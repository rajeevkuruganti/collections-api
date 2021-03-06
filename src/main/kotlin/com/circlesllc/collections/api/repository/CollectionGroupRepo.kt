package com.circlesllc.collections.api.repository

import com.circlesllc.collections.api.entities.CollectionGroup
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CollectionGroupRepo: CrudRepository<CollectionGroup, Long> {
}