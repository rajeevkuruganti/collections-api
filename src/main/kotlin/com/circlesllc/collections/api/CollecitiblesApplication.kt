package com.circlesllc.collections.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableTransactionManagement
class CollectiblesA pplication

fun main(args: Array<String>) {
    runApplication<CollectiblesApplication>(*args)

}

