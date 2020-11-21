package com.circlesllc.collectibles.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/collection")
class CollectionController {

    @GetMapping("/hello")
    fun helloWorld(): String{
         return "Hello World! "
    }

    @GetMapping("/info")
    fun getInfo(): String{
        return "This is the Collectible Application. In this application, you can store your collections and organize them. "
    }

}