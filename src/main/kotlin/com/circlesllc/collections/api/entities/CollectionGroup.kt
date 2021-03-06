package com.circlesllc.collections.api.entities

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collection_group")
data class CollectionGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @Column
        val name: String,
        val collectionobj: String


) {

}
