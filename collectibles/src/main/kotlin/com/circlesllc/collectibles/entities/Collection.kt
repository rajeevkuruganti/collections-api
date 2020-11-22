package com.circlesllc.collectibles.entities

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
data class CollectionGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Number,

        val name: String,

        val collectionObj: String


) {

}
