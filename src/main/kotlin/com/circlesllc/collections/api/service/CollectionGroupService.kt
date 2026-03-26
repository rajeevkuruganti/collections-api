package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
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
        } catch (nfe: Exception) {
            log.error("NOT FOUND " + nfe.message)
            false
        }
    }

    fun findByName(inputName: String): Optional<CollectionGroup> {
        return try {
            collectionGroupRepo.findByName(inputName)
        } catch (nfe: Exception) {
            log.error("NOT FOUND " + nfe.message)
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
            log.error("NOT FOUND " + nfe.message.toString())
            return Optional.empty()

        }
    }

    fun updateItem(storedItemId: Long, collectionGroupEdited: CollectionGroupDO): CollectionGroup {
        val storedItemOptional: Optional<CollectionGroup> = collectionGroupRepo.findById(storedItemId)
        if (storedItemOptional.isEmpty) {
            log.error("Item with id $storedItemId not found")
            false
        }
        val storedItem = storedItemOptional.get()

        val json = Json { ignoreUnknownKeys = true }
        val storedJson = json.parseToJsonElement(storedItem.itemcontents).jsonObject
        log.info("storedJson = $storedJson")
        val newJsonValues = json.parseToJsonElement(collectionGroupEdited.itemcontents).jsonObject
        log.info("newJsonValues = $newJsonValues")
        val updatedJsonObject = JsonObject(storedJson.toMutableMap().apply {
            this.putAll(newJsonValues)
        })
        log.info("updatedJsonObject = $updatedJsonObject")
        storedItem.name = collectionGroupEdited.name
            .takeIf { it != storedItem.name } ?: storedItem.name

        storedItem.itemcontents = updatedJsonObject.toString()
        return collectionGroupRepo.save(storedItem)
    }

}
