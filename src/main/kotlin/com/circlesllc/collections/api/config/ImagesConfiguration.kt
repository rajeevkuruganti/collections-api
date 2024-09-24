package com.circlesllc.collections.api.config

import lombok.Getter
import lombok.Setter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

//@Configuration
@ConfigurationPropertiesScan
data class ImagesConfiguration /*@ConstructorBinding constructors*/ (
//    var collectionImagesS3Bucket: String = "collection-images", //  collection.images.s3.bucket
//    var minioS3AccessKey: String = "Z09TXYzfmu2q01ScKRfy",// minio.s3.access.key
//    var minioS3SecretKey: String  ="NnPLNtYCnZsWyPZfrxOlnaedkEi7c9hykFs14wj5"//minio.s3.secret.key
     val collectionImagesS3Bucket: String,
    val minioS3AccessKey: String ,
    var minioS3SecretKey: String
) {



}