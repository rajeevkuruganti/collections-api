package com.circlesllc.collectibles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableTransactionManagement
class CollectiblesApplication

fun main(args: Array<String>) {
    runApplication<CollectiblesApplication>(*args)

}

