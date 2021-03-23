package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import org.apache.tomcat.util.json.JSONParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
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

    @PostMapping("/cs1")
    fun saveNewItem(@RequestBody collectionGroup: CollectionGroup): CollectionGroup {
//        var collectionGroup: CollectionGroup(23434,"Classics" ,"tobeDeleted",item)
//        collectionGroup.itemContents = item
//        collectionGroup.name = "Classics"
//        collectionGroup.collectionobj = "to be deleted"
        println(collectionGroup.itemcontents)
        println(collectionGroup.name)
//        val someJson = """{"name":"Rajeev","title":"Architect"}"""
//        collectionGroup.itemcontents=  someJson
        println(collectionGroup.name)
        println("did you make it here???")
        return  collectionGroupRepo.save(collectionGroup)
    }

    @GetMapping("/cs/one")
    fun getOne(): Optional<CollectionGroup> {
        return collectionGroupRepo.findById(13)

    }

}


