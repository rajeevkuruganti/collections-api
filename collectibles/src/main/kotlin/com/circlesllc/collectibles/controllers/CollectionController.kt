package com.circlesllc.collectibles.controllers

import com.circlesllc.collectibles.entities.CollectionGroup
import com.circlesllc.collectibles.repository.CollectionGroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/collection")
class CollectionController() {
    @Autowired
    private lateinit var collectionGroupRepo: CollectionGroupRepo

    @GetMapping("/hello")
    fun helloWorld(): String {
        return "Hello World! "
    }

    @GetMapping("/info")
    fun getInfo(): String {
        return "This is the Collectible Application. In this application, you can store your collections and organize them. "
    }

    @GetMapping("/cs")
    fun getAll(): MutableIterable<CollectionGroup> {

        return collectionGroupRepo.findAll()
    }

    @GetMapping("/cs/one")
    fun getOne(): Optional<CollectionGroup> {
        return collectionGroupRepo.findById(13)

    }

}