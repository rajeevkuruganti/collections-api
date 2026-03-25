package com.circlesllc.collections.api.controllers

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.service.CollectionGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/collection")
class CollectionController() {

    @Autowired
    private lateinit var collectionGroupService: CollectionGroupService



    @GetMapping("/cs")
    fun getAll(): ResponseEntity<MutableIterable<CollectionGroup>> {
        if (collectionGroupService.findAll() != null) {
            return ResponseEntity.ok(collectionGroupService.findAll())
        }
        return ResponseEntity.notFound().build()

    }


    @GetMapping("/cs/{collectionId}")
    fun getOne(
        @PathVariable collectionId: Long
    ): Any {
        val myItem = collectionGroupService.findById(collectionId)
        if (myItem != null) {
            return ResponseEntity.ok(myItem)
        }
        return ResponseEntity.notFound()
    }

    @GetMapping("/cs/name/{collectionName}")
    fun getByName(
        @PathVariable collectionName: String
    ): Any {
        val myItem = collectionGroupService.findByName(collectionName)
        if (myItem != null) {
            return ResponseEntity.ok(myItem)
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

    @PatchMapping("/cs/{collectibleId}")
    fun updateItem(
        @PathVariable collectibleId: Long,
        @RequestBody collectionGroupEdited: CollectionGroupDO
    ): CollectionGroup {
        val myStoredItem = collectionGroupService.updateItem(collectibleId, collectionGroupEdited)
        return myStoredItem
    }

    @GetMapping("/info")
    fun getInfo(): String {
        return "This is the Collectible Application. " +
                "In this application, you can store your collections and organize them." +
                "Future Enhancements TODO::" +
                " 1.  Use env vars for most application.yaml file so we have one " +
                " 1.5 Update README.MD with the env variables needed  " +
                " 2.  Use NO IMAGES For REnder deploymnet - maybe still undecided" +
                " 3.  refresh after update a record and fix teh service to update name also  " +
                " 4.  Update the delete to use delete flag" +
                " 5.  Duplicate Records so user can edit a few stuff            " +
                " 6.  Possibly later on have tabs with 'Books','Coins','Music','Art','Misc'"
    }

}


