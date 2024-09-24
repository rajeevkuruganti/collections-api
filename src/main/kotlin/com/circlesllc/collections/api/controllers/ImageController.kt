package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.config.ImagesConfiguration
import com.circlesllc.collections.api.dataobject.Image
import io.minio.*
import io.minio.errors.MinioException
import io.minio.http.Method
import io.minio.messages.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit


@CrossOrigin("*")
@RestController
@RequestMapping("/collection/images")
class ImageController {
    @Value(value="\${collection.images.s3.bucket}")
    val minioBucket:String? = null
    @Value("\${minio.s3.access.key}")
    var minioAccesKey: String? = null
    @Value("\${minio.s3.secret.key}")
    var minioSecretKey: String? = null
    @Value("\${minio.s3.url}")
    var minioS3URL: String? = null

    @GetMapping
    fun getListOfImages() {
        var list:ArrayList<String>

        println("minioBucket: $minioBucket")
        println("minioAccessKey: $minioAccesKey")
        println("minioSecretKey: ${minioSecretKey}")
        println("minioS3Url: ${minioS3URL}")
        val reqParams: MutableMap<String, String> = HashMap()
        reqParams["response-content-type"] = "application/json"

        // Initialize minio client object.
        var minioClient: MinioClient = MinioClient.builder()
            .endpoint("http://127.0.0.1:9000")
            .credentials("Z09TXYzfmu2q01ScKRfy", "NnPLNtYCnZsWyPZfrxOlnaedkEi7c9hykFs14wj5")
            .build()
//        list = minioClient.listBuckets()
        val found =
            minioClient.bucketExists(BucketExistsArgs.builder().bucket("collection-images").build())
        if (found) {
            println("Yes !!! I connected to my bucket exists")
            val results: MutableIterable<io.minio.Result<Item>>? = minioClient.listObjects(
                ListObjectsArgs.builder()
                    .bucket("collection-images")
                    .build()
            )
           var list: ArrayList<Image>
            for (result in results!!) {
                val item = result.get()
                println("${item.lastModified()}, ${item.size()}, ${item.objectName()}")
                var objectNameValue: String = item.objectName()
                val url =
                        minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket("collection-images")
                                .`object`(objectNameValue)
                                .expiry(2, TimeUnit.HOURS)
                                .build()
                        )
                    println("URL is  ${url}")
                    val image: Image = Image(item.lastModified(), item.size(), item.objectName(), url)
                    println(image.toString())
            }

//            results.iterator().forEach { println() }
        } else {
            println("Now what?? does not exist. We create it!")
            minioClient.makeBucket(
                MakeBucketArgs
                    .builder()
                    .bucket(minioBucket)
                    .build()
            )
            println("created collection-images")

        }
    }
    @PostMapping
    fun putImageInS3(){
        println("in Post mapping")
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            val minioClient =
                MinioClient.builder()
                    .endpoint("http://127.0.0.1:9000")
//                    .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                    .build()

            // Make 'asiatrip' bucket if not exist.
            val found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket("collection-images").build())
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("collection-images").build())
            } else {
                println("Bucket 'asiatrip' WJHAT  already exists.")
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                UploadObjectArgs.builder()
                    .bucket("collection-images")
                    .`object`("asiaphotos-2015.zip")
                    .filename("/home/user/Photos/asiaphotos.zip")
                    .build()
            )
            println(
                "'/home/rajeevk/temp/someImage.png' is successfully uploaded as "
                        + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'."
            )
        } catch (e: MinioException) {
            println("Error occurred: $e")
            println("HTTP trace: " + e.httpTrace())
        }
    }

}