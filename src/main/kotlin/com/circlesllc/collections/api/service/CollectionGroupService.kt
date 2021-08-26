package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import org.apache.tomcat.util.json.JSONParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
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

}
