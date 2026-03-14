package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CollectionGroupService {
    companion object {
        private val log = LoggerFactory.getLogger(CollectionGroupService::class.java)
    }

    @Autowired
    lateinit var collectionGroupRepo: CollectionGroupRepo


    fun saveNewItem(collectionGroupDO: CollectionGroupDO): CollectionGroup {
        val collectionGroup = CollectionGroup(
            0L, collectionGroupDO.name,
            collectionGroupDO.itemcontents
        )
        return collectionGroupRepo.save(collectionGroup)

    }

    fun deleteById(collectionId: Long): Boolean {
        return try {
            collectionGroupRepo.deleteById(collectionId)
            true
        } catch (nfe: Exception ) {
            false
        }
    }

    fun findByName(inputName: String): Optional<CollectionGroup> {
        return try {
            collectionGroupRepo.findByName(inputName)
        } catch (nfe: Exception) {
            Optional.empty()
        }

    }

    @Suppress("UseExpressionBody")
    fun findAll(): MutableIterable<CollectionGroup> {
        return collectionGroupRepo.findAll()

    }

    fun findById(collectionId: Long): Optional<CollectionGroup> {
        try {
            return collectionGroupRepo.findById(collectionId)
        } catch (nfe: Exception) {
            log.error("NOT FOUND")
            return Optional.empty()

        }
    }

}
