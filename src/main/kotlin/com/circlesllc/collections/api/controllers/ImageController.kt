package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.Image
import com.circlesllc.collections.api.service.ImageServiceS3
import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.UploadObjectArgs
import io.minio.errors.MinioException
import org.slf4j.LoggerFactory
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
        private val log = LoggerFactory.getLogger(ImageController::class.java)
        // Constants moved to application.yaml
    }

    @GetMapping
    fun getListOfImages(): List<Image> {
        return imageServiceS3.images()
    }



    @PostMapping
    fun putImageInS3(){
        log.info("in Post mapping")
        try {
            // Use the service to get the minio client
            val minioClient = imageServiceS3.getMinioClient()
            if (minioClient == null) {
                log.warn("Could not initialize MinioClient")
                return
            }

            // Make 'asiatrip' bucket if not exist.
            val found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())
//            if (!found) {
//                // Make a new bucket called 'asiatrip'.
//                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build())
//            } else {
//                log.info("Bucket 'asiatrip' WJHAT  already exists.")
//            }

            // TODO: Remove hardcoded test code - this should accept file upload parameters
             minioClient.uploadObject(
                 UploadObjectArgs.builder()
                     .bucket(bucketName)
                     .`object`("rajeevk/storedFile1.png")  /// STORED FILE NAME WITH PATH TO MINO
//                     .filename("/home/rajeevk/Desktop/Pics/WalkingTree.jpg")
                     .filename("/Users/rajeevk/Desktop/Pics/WalkingTree.jpg") /// THE FILE NAME from DESKTOP sent by UI
                     .build()
             )
             println(
                 "'WalkingTree.jpg' is successfully uploaded as "
             )
        } catch (e: MinioException) {
            log.error("Error occurred: $e")
            log.error("HTTP trace: " + e.httpTrace())
        }
    }

}