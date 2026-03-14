package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.Image
import io.minio.*
import io.minio.messages.Bucket
import io.minio.messages.Item
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

internal class ImageServiceS3Test {

    private lateinit var minioClient: MinioClient
    private lateinit var imageService: ImageServiceS3
    private val bucketName = "test-bucket"

    @BeforeEach
    fun setUp() {
        minioClient = mockk()
        imageService = ImageServiceS3(
            minioBucket = bucketName,
            minioAccesKey = "access",
            minioSecretKey = "secret",
            minioS3URL = "http://localhost:9000"
        )
        imageService.setMinioClient(minioClient)
    }

    @Test
    fun `images should return list of images when bucket exists`() {
        // Arrange
        val bucket = mockk<Bucket>()
        every { bucket.name() } returns bucketName
        every { minioClient.listBuckets() } returns mutableListOf(bucket)
        every { minioClient.bucketExists(any()) } returns true

        val item = mockk<Item>()
        val now = ZonedDateTime.now()
        every { item.lastModified() } returns now
        every { item.size() } returns 100L
        every { item.objectName() } returns "image1.jpg"

        val resultItem = mockk<Result<Item>>()
        every { resultItem.get() } returns item
        
        val results = listOf(resultItem)
        every { minioClient.listObjects(any()) } returns results

        val presignedUrl = "http://presigned-url.com/image1.jpg"
        every { minioClient.getPresignedObjectUrl(any()) } returns presignedUrl

        // Act
        val images = imageService.images()

        // Assert
        assertEquals(1, images.size)
        val image = images[0]
        assertEquals("image1.jpg", image.objectName)
        assertEquals(presignedUrl, image.url)
        assertEquals(100L, image.size)
        assertEquals(now, image.lastModified)

        verify { minioClient.listBuckets() }
        verify { minioClient.bucketExists(any()) }
        verify { minioClient.listObjects(any()) }
        verify { minioClient.getPresignedObjectUrl(any()) }
    }

    @Test
    fun `images should create bucket when it does not exist`() {
        // Arrange
        every { minioClient.listBuckets() } returns mutableListOf()
        every { minioClient.bucketExists(any()) } returns false
        every { minioClient.makeBucket(any()) } returns Unit

        // Act
        val images = imageService.images()

        // Assert
        assertTrue(images.isEmpty())
        verify { minioClient.bucketExists(any()) }
        verify { minioClient.makeBucket(any()) }
    }

    @Test
    fun `images should return empty list when client is null`() {
        // Arrange
        val serviceWithoutClient = ImageServiceS3(
            minioBucket = bucketName,
            minioAccesKey = null, // This will make getMinioClient return null if minioClient is not set
            minioSecretKey = null,
            minioS3URL = null
        )

        // Act
        val images = serviceWithoutClient.images()

        // Assert
        assertTrue(images.isEmpty())
    }
}
