package com.circlesllc.collectibles.repository

import com.circlesllc.collectibles.entities.CollectionGroup
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CollectionGroupRepo: CrudRepository<CollectionGroup, Long> {
}