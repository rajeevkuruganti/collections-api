package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.Image
import io.minio.*
import io.minio.http.Method
import io.minio.messages.Bucket
import io.minio.messages.Item
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit


@Service
@Slf4j
class ImageServiceS3(
    @Value(value = "\${collection.images.s3.bucket}")
    val minioBucket: String,
    @Value("\${minio.s3.access.key}")
    var minioAccesKey: String?,
    @Value("\${minio.s3.secret.key}")
    var minioSecretKey: String?,
    @Value("\${minio.s3.url}")
    var minioS3URL: String?
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
        private const val CONTENT_TYPE_JSON = "application/json"
        private const val PARAM_RESPONSE_CONTENT_TYPE = "response-content-type"
        private const val URL_EXPIRY_DURATION = 2
        private val URL_EXPIRY_UNIT = TimeUnit.HOURS
    }

    fun images(): ArrayList<Image> {
        var list: ArrayList<Image> = ArrayList<Image>()
        var listUrls: ArrayList<String> = ArrayList()

        val reqParams: MutableMap<String, String> = HashMap()
        reqParams[PARAM_RESPONSE_CONTENT_TYPE] = CONTENT_TYPE_JSON
        val client = getMinioClient()
        if (client != null) {
            val bucketList: MutableList<Bucket>? = client.listBuckets()
            bucketList?.forEach({ bucket -> println(bucket.name()) })

            val found =
                client.bucketExists(BucketExistsArgs.builder().bucket(minioBucket).build())
            if (found) {
                println("Yes !!! I connected to my bucket exists")
                val results: MutableIterable<Result<Item>>? = client.listObjects(
                    ListObjectsArgs.builder()
                        .bucket(minioBucket)
                        .build()
                )
                for (result in results!!) {
                    val item = result.get()
                    println("${item.lastModified()}, ${item.size()}, ${item.objectName()}")
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
                    println("URL is  ${url}")
                    val image: Image = Image(item.lastModified(), item.size(), item.objectName(), url)
                    list.add(image)
                    listUrls.add(url)
                    println(image.toString())
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
                println("created $minioBucket")

            }
        } else {
            println("Do not have any variables to connect to Minio !")
        }
        return list
    }
}