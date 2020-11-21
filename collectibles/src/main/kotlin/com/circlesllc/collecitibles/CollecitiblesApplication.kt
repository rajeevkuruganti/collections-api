package com.circlesllc.collecitibles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class CollecitiblesApplication

fun main(args: Array<String>) {
	runApplication<CollecitiblesApplication>(*args)
}
