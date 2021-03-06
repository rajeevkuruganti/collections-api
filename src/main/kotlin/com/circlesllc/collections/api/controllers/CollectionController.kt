package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@CrossOrigin("*")
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