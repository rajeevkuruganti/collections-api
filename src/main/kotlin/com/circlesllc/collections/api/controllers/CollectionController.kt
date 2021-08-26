package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import com.circlesllc.collections.api.service.CollectionGroupService
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

    @Autowired
    private lateinit var collectionGroupService: CollectionGroupService

    @GetMapping("/info")
    fun getInfo(): String {
        return "This is the Collectible Application. In this application, you can store your collections and organize them. "
    }

    @GetMapping("/cs")
    fun getAll(): MutableIterable<CollectionGroup> {
        return collectionGroupRepo.findAll()
    }

    @PostMapping("/cs")
    fun saveNewItem(@RequestBody collectionGroupNew: CollectionGroupDO): CollectionGroup {
        return  collectionGroupService.saveNewItem(collectionGroupNew)
    }

    @GetMapping("/cs/one")
    fun getOne(): Optional<CollectionGroup> {
        return collectionGroupRepo.findById(13)

    }

}


