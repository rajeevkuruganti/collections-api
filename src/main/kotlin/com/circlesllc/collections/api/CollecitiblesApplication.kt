package com.circlesllc.collections.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
@EnableTransactionManagement
class CollectiblesApplication

fun main(args: Array<String>) {
    runApplication<CollectiblesApplication>(*args)

}

@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("http://localhost:5173") // Your React dev server
        config.addAllowedOrigin("http://localhost:3000") // Alternative React port
        config.addAllowedHeader("*")
        config.addAllowedMethod("*") // Allows GET, POST, PUT, DELETE, etc.
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}