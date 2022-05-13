package com.circlesllc.collections.api.repository

import com.circlesllc.collections.api.entities.CollectionGroup
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CollectionGroupRepo: CrudRepository<CollectionGroup, Long> {
   // to find by name a collectionGroup
   fun findByName(findName: String) : Optional<CollectionGroup>
}