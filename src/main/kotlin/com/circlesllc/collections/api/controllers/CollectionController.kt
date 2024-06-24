package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.service.CollectionGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin("*")
@RestController
@RequestMapping("/collection")
class CollectionController() {


    @Autowired
    private lateinit var collectionGroupService: CollectionGroupService

    @GetMapping("/info")
    fun getInfo(): String {
        return "This is the Collectible Application. In this application, you can store your collections and organize them. "
    }

    @GetMapping("/cs")
    fun getAll(): ResponseEntity<MutableIterable<CollectionGroup>> {
        if (collectionGroupService.findAll() != null) {
            return ResponseEntity.ok(collectionGroupService.findAll())
        }
        return ResponseEntity.notFound().build()

    }


    @GetMapping("/cs/{collectionId}{entryDate}")
    fun getOne(
            @PathVariable collectionId: Long
    ): Any {
        var myCollection = collectionGroupService.findById(collectionId)
        if (myCollection != null) {
            return ResponseEntity.ok(myCollection)
        }
        return ResponseEntity.notFound()
    }

    @GetMapping("/cs/name/{collectionName}")
    fun getByName(
            @PathVariable collectionName: String
    ): Any {
        val myCollection = collectionGroupService.findByName(collectionName.toString())
        if (myCollection != null) {
            return ResponseEntity.ok(myCollection)
        }
        return ResponseEntity.notFound()
    }

    @PostMapping("/cs")
    fun saveNewItem(@RequestBody collectionGroupNew: CollectionGroupDO): CollectionGroup {
        return collectionGroupService.saveNewItem(collectionGroupNew)
    }

    @DeleteMapping("/cs/{collectionId}")
    fun deleteOne(
            @PathVariable collectionId: Long
    ): Boolean {
        return collectionGroupService.deleteById(collectionId)
    }

}


