package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.Image
import io.minio.*
import io.minio.errors.MinioException
import io.minio.http.Method
import io.minio.messages.Item
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit


@CrossOrigin("*")
@RestController
@RequestMapping("/images")

class ImageController {

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

    @GetMapping
    fun getListOfImages() {
        var list:ArrayList<String>
        var minioBucket:String = "collection-images"
        val reqParams: MutableMap<String, String> = HashMap()
        reqParams["response-content-type"] = "application/json"

        // Initialize minio client object.
        var minioClient: MinioClient = MinioClient.builder().endpoint("http://127.0.0.1:9000")
            .credentials("HPRlniIGF5m5i5PwbXq6",
                "L1ofqvjAsae3KiehBkDTXO2o9NXXgFZ07wnQwg20")
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
            println("Now what?? does not exist")
        }
    }


}