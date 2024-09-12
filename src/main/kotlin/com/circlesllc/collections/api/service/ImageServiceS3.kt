package com.circlesllc.collections.api.service

import io.minio.MinioClient
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service


@Service
@Slf4j
class ImageServiceS3 {

    fun getObject(name: String): Object? {
        val obj: Unit
        val minioClient = MinioClient.builder()
            .endpoint("http://127.0.0.1:9000")
            .credentials("minioadmin", "minioadmin")
            .build()
//        obj = minioClient.getObject(
//            GetObjectArgs
//                .builder()
//                .bucket("collection-images")
//                .`object`("eastContinentalDivide.jpg")
//                .build()
//        ).use {
//            stream -> (obj != null) ? println("sucess"): println("nokpe")}
//
//          // List all objects.
//        val myObjects: Iterable<Result<Item>> = minioClient.listObjects("collection-images")
//        // Iterate over each elements and set album url.
//        for (result in myObjects) {
//            val item: Item = result.getOrThrow()
//            println(buildString {
//                append((item.lastModified() + ", " + item.size()).toString())
//                append(", ")
//                append(item.objectName())
//            })
//
//            // Generate a presigned URL which expires in a day
//            val url = minioClient.getPresignedObjectUrl(minioBucket, item.objectName(), 60 * 60 * 24)
//
//            // Create a new Album Object
//            val album = Album()
//
//            // Set the presigned URL in the album object
//            album.setUrl(url)
//
//            // Add the album object to the list holding Album objects
//            list.add(album)
//        }
//
//        // Return list of albums.
//
//        // Return list of albums.
//        return list
//    }
        return null
    }
}