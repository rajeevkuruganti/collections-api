package com.circlesllc.collections.api.entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collection_group")
data class CollectionGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    @Column
    val name: String,
    // the type for jsonb is important and so also the column definition for saving into the table
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = true)
    var itemcontents: String

) {

}
