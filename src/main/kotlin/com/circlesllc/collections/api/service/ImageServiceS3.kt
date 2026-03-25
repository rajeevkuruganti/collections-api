package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.Image
import io.minio.*
import io.minio.http.Method
import io.minio.messages.Bucket
import io.minio.messages.Item
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.concurrent.TimeUnit


@Service
class ImageServiceS3(
    @Value(value = "\${collection.images.s3.bucket}")
    val minioBucket: String,
    @Value("\${minio.s3.access.key}")
    var minioAccesKey: String?,
    @Value("\${minio.s3.secret.key}")
    var minioSecretKey: String?,
    @Value("\${minio.s3.url}")
    var minioS3URL: String?,
    val MINIO_PATH: String = "/rajeevk/item_id"
) {

    private var minioClient: MinioClient? = null

    fun getMinioClient(): MinioClient? {
        if (minioClient == null && minioS3URL != null && minioAccesKey != null && minioSecretKey != null) {
            minioClient = MinioClient.builder()
                .endpoint(minioS3URL)
                .credentials(minioAccesKey, minioSecretKey)
                .build()
        }
        return minioClient
    }

    // For testing purposes
    fun setMinioClient(client: MinioClient) {
        this.minioClient = client
    }

    companion object {
        private val log = LoggerFactory.getLogger(ImageServiceS3::class.java)
        private const val CONTENT_TYPE_JSON = "application/json"
        private const val PARAM_RESPONSE_CONTENT_TYPE = "response-content-type"
        private const val URL_EXPIRY_DURATION = 2
        private val URL_EXPIRY_UNIT = TimeUnit.HOURS
    }

    fun images(): ArrayList<Image> {
//        val list: MutableList = MutableList<Image>()
        val list: ArrayList<Image> = ArrayList<Image>()
        val listUrls: ArrayList<String> = ArrayList()

        val reqParams: MutableMap<String, String> = HashMap()
        reqParams[PARAM_RESPONSE_CONTENT_TYPE] = CONTENT_TYPE_JSON
        val client = getMinioClient()
        if (client != null) {
            val bucketList: MutableList<Bucket>? = client.listBuckets()
            bucketList?.forEach({ bucket -> log.info(bucket.name()) })

            val found =
                client.bucketExists(BucketExistsArgs.builder().bucket(minioBucket).build())
            if (found) {
                log.info("Yes !!! I connected to my bucket exists")
                val results: MutableIterable<Result<Item>>? = client.listObjects(
                    ListObjectsArgs.builder()
                        .bucket(minioBucket)
                        .prefix("newpath/")   // your “folder”
                        .recursive(true)
                        .build()
                )
                log.info("results = $results.toString()")

                for (result in results!!) {
                    val item = result.get()
                    // Skip directory-like entries (no real object content)
                    if (item.isDir) continue

                    log.info("${item.lastModified()}, ${item.size()}, ${item.objectName()}")
                    val objectNameValue: String = item.objectName()
                    val url =
                        client.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(minioBucket)
                                .`object`(objectNameValue)
                                .expiry(URL_EXPIRY_DURATION, URL_EXPIRY_UNIT)
                                .build()
                        )
                    val image: Image = Image(item.lastModified(), item.size(), item.objectName(), url)
                    log.info("${item.lastModified()}, ${item.size()}, ${item.objectName()}")
                    log.info(url)
                    list.add(image)
                    listUrls.add(url)
                    log.info(image.toString())
                }
            } else {
                println("Now what?? does not exist. We create it!")
                // Here we create the bucket for the userId and under that the collectible item.
                client.makeBucket(
                    MakeBucketArgs
                        .builder()
                        .bucket(minioBucket)
                        .build()
                )
                log.info("created $minioBucket")

            }
        } else {
            log.warn("Do not have any variables to connect to Minio !")
        }

        return list
    }

    fun deleteImage(storedImageFileName: String, bucketName: String, userId: String): Boolean {
        val minioClient = getMinioClient()
        try {
            val storedFileNameMinio: String = MINIO_PATH + storedImageFileName
            minioClient?.removeObject(
                RemoveObjectArgs.builder().bucket(bucketName).`object`(storedFileNameMinio).build()
            )
        } catch (e: Exception) {
            log.error("Exception in deleteImage Service side")
            e.printStackTrace()
            return false
        }
        return true
    }

    fun storeImage(fileNameGiven: MultipartFile, bucketName: String, userId: String) {
        try {
            log.info("in storeImage Service side" + fileNameGiven.originalFilename + " bucketName =" + bucketName)

            val minioFileName: String = generateMinioFileName("rajeevk", fileNameGiven.originalFilename)
            val minioClient = getMinioClient()
            fileNameGiven.inputStream.use { inputStream ->
                minioClient?.putObject(
                    PutObjectArgs.builder()
                        .bucket(bucketName)
                        .`object`(minioFileName)
                        .stream(inputStream, fileNameGiven.size, -1)
                        .contentType(fileNameGiven.contentType ?: "application/octet-stream")
                        .build()
                )
            }

            log.info("{${fileNameGiven.originalFilename}} is successfully uploaded as {$minioFileName}")

        } catch (e: Exception) {
            log.error("Exception in storeImage Service side")
            e.printStackTrace()
        }
    }

    fun generateMinioFileName(userId: String, fileNameGiven: String?): String {
        if (fileNameGiven == null) return "ImageName issue"
        var minioFileName: String = MINIO_PATH + fileNameGiven
        return minioFileName
    }

}