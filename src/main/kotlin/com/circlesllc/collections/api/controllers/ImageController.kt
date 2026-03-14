package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.Image
import com.circlesllc.collections.api.service.ImageServiceS3
import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.UploadObjectArgs
import io.minio.errors.MinioException
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*


@CrossOrigin("*")
@RestController
@RequestMapping("/collection/images")
class ImageController(
    val imageServiceS3: ImageServiceS3,
    @Value("\${collection.images.s3.bucket}")
    val bucketName: String,
    @Value("\${minio.s3.url}")
    val minioUrl: String
) {

    companion object {
        // Constants moved to application.yaml
    }

    @GetMapping
    fun getListOfImages(): List<Image> {
        return imageServiceS3.images()
    }



    @PostMapping
    fun putImageInS3(){
        println("in Post mapping")
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            val minioClient =
                MinioClient.builder()
                    .endpoint(minioUrl)
//                    .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                    .build()

            // Make 'asiatrip' bucket if not exist.
            val found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build())
            } else {
                println("Bucket 'asiatrip' WJHAT  already exists.")
            }

            // TODO: Remove hardcoded test code - this should accept file upload parameters
            // minioClient.uploadObject(
            //     UploadObjectArgs.builder()
            //         .bucket(bucketName)
            //         .`object`("asiaphotos-2015.zip")
            //         .filename("/home/user/Photos/asiaphotos.zip")
            //         .build()
            // )
            // println(
            //     "'/home/rajeevk/temp/someImage.png' is successfully uploaded as "
            //             + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'."
            // )
        } catch (e: MinioException) {
            println("Error occurred: $e")
            println("HTTP trace: " + e.httpTrace())
        }
    }

}