package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import javassist.NotFoundException
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
@Slf4j
class CollectionGroupService {
    @Autowired
    private lateinit var collectionGroupRepo: CollectionGroupRepo


    fun saveNewItem(collectionGroupDO: CollectionGroupDO): CollectionGroup {
        val jsonItemContents = """"${collectionGroupDO.itemcontents}""""
        var collectionGroup = CollectionGroup(
            0L, collectionGroupDO.name,
            collectionGroupDO.itemcontents
        )
        return collectionGroupRepo.save(collectionGroup)

    }

    fun deleteById(collectionId: Long): Boolean {
        if (collectionGroupRepo.findById(collectionId) != null ) {
            collectionGroupRepo.deleteById(collectionId)
            return true
        }
        return false
    }

    fun findByName(inputName: String): Optional<CollectionGroup> {
        try {
            return collectionGroupRepo.findByName(inputName)
        } catch (nfe: NotFoundException){

            return Optional.empty()
        }

    }

    fun findAll(): MutableIterable<CollectionGroup> {
       return  collectionGroupRepo.findAll();

    }

    fun findById(collectionId: Long): Optional<CollectionGroup> {
        try {
            return collectionGroupRepo.findById(collectionId)
        } catch (nfe: NotFoundException){
            println("NOT FOUND")
            return Optional.empty()

        }
    }

}
