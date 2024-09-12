package com.circlesllc.collections.api.dataobject

import com.fasterxml.jackson.annotation.JsonRootName
import lombok.AllArgsConstructor
import java.time.ZonedDateTime

@JsonRootName("Image")
@AllArgsConstructor
data class Image(val lastModified: ZonedDateTime, val size: Long, val objectName: String, val url: String) {
//       val  objectName = objectName
//       val lastModified: ZonedDateTime = lastModified
//       val url: String = url
//       val size:Long = size


override fun toString(): String {
    return "last modified = ${lastModified}  url = ${url}"
}
}