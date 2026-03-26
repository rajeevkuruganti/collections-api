package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.Image
import com.circlesllc.collections.api.service.ImageServiceS3
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@CrossOrigin("*")
@RestController
@RequestMapping("/collection/images")
class ImageController(
    val imageServiceS3: ImageServiceS3,
    @Value("\${collection.images.s3.bucket}")
    val bucketName: String,
) {

    companion object {
        private val log = LoggerFactory.getLogger(ImageController::class.java)
        // Constants moved to application.yaml
    }

    @GetMapping
    fun getListOfImages(): List<Image> {
        return imageServiceS3.getImages()
    }

    @DeleteMapping("/{*storedImageFileName}")
    fun deleteImage(@PathVariable storedImageFileName: String): Boolean {
        log.info("in deleteImage Controller side" + storedImageFileName)
        return imageServiceS3.deleteImage(storedImageFileName, bucketName, "")
    }

    // Add @RequestBody to the parameter to send jpg, mvoie, png etc.
    @PostMapping("/upload/")
    fun putImageInS3(@RequestParam fileName: MultipartFile): Boolean {
        // get UserId from JWT token or ask the UI to pass it in the request.
        try {
            imageServiceS3.storeImage(fileName, bucketName, "")
            return true
        } catch (e: Exception) {
            log.error("Exception in putImageInS3 Controller side")
            log.info("in Post mapping= " + fileName.originalFilename + " bucketName =" + bucketName)
            e.printStackTrace()
            return false
        }
    }

}