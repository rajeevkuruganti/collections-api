package com.circlesllc.collections.api


import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.Arrays


@SpringBootApplication
@EnableTransactionManagement

class CollectiblesApplication

fun main(args: Array<String>) {
    runApplication<CollectiblesApplication>(*args)

}
@Component
@ConfigurationProperties(prefix = "cors")
data class CorsProps(
    var allowedOrigins: List<String> = emptyList()
)
@Configuration
class CorsConfig(
    private val corsProps: CorsProps
) : WebMvcConfigurer {

    companion object {
        private const val CORS_MAPPING_PATTERN = "/**"
        private val ALLOWED_HTTP_METHODS = arrayOf("GET", "POST", "PUT","PATCH", "DELETE", "OPTIONS")
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping(CORS_MAPPING_PATTERN)
            .allowedOrigins(*corsProps.allowedOrigins.toTypedArray())
            .allowedMethods(*ALLOWED_HTTP_METHODS)
    }
}
