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
        //        var collectionGroup: CollectionGroup(23434,"Classics" ,"tobeDeleted","{}")
//        println(collectionGroupDO.category)
//        println(collectionGroupDO.name)
//        val someJson = """{"name":"Rajeev","title":"Architect"}"""
//        collectionGroup.itemcontents=  someJson
//        println(collectionGroupDO.name)
        println("in the SERVICE")
        println(collectionGroupDO.collectionobj)
        println(collectionGroupDO.itemcontents)
//        val parser: JSONParser: JSONParser = new JSONParser()
//        val json: JSONObject = parser.parse(collectionGroupDO.itemcontents) as JSONObject
        println("did you make it here???")
        var collectionGroup = CollectionGroup(0L, collectionGroupDO.name, collectionGroupDO.collectionobj,
            " {itemContents:$collectionGroupDO.itemcontents} :: json")
        return collectionGroupRepo.save(collectionGroup)

    }

}
