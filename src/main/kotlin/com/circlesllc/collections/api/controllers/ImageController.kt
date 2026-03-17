package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.Image
import com.circlesllc.collections.api.service.ImageServiceS3
import io.minio.BucketExistsArgs
import io.minio.UploadObjectArgs
import io.minio.errors.MinioException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


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

    @DeleteMapping("/{storedImageFileName}")
    fun deleteImage(@PathVariable storedImageFileName: String): Boolean {
        return imageServiceS3.deleteImage(storedImageFileName)
    }

    // Add @RequestBody to the parameter to send jpg, mvoie, png etc.
    @PostMapping("/upload/")
    fun putImageInS3(@RequestParam fileName: MultipartFile): Boolean {
        log.info("in Post mapping= " + fileName.originalFilename + " bucketName =" + bucketName)
        imageServiceS3.storeImage(fileName,bucketName,)
     return true
    }

}