package com.circlesllc.collections.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
@EnableTransactionManagement
class CollectiblesApplication

fun main(args: Array<String>) {
    runApplication<CollectiblesApplication>(*args)

}
@Bean
fun configure(): WebMvcConfigurer {
    return object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/*")
                .allowedOrigins("*")
        }
    }
}