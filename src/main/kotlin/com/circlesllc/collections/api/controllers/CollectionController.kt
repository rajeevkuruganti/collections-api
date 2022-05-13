package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import com.circlesllc.collections.api.service.CollectionGroupService
import lombok.extern.slf4j.Slf4j
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
        return ResponseEntity.ok(collectionGroupService.findAll())
    }


    @GetMapping("/cs/{collectionId}")
    fun getOne(
        @PathVariable collectionId: Long
    ): ResponseEntity<CollectionGroup>{
        var byIdObject = collectionGroupService.findById(collectionId)
//        return byIdObject != null? ResponseEntity.ok(byIdObject):  ResponseEntity.notFound()
        return  ResponseEntity.of(byIdObject)


    }

    @GetMapping("/cs/byname/{collectionName}")
    fun getByName(
        @PathVariable collectionName: String
    ): Optional<CollectionGroup>? {
        println("are you here collectionName =  $collectionName")
        println(collectionName)
//        log.info("you are here")
        val x = collectionGroupService.findByName(collectionName.toString())
        return x
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


