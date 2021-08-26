package com.circlesllc.collections.api.entities

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef

import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collection_group")
@TypeDef(name="jsonb", typeClass = JsonBinaryType::class)
data class CollectionGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @Column
        val name: String,
        // the type for jsonb is important and so also the column definition for saving into the table
        @Type(type="jsonb")
        @Column(columnDefinition = "jsonb", nullable = true)
        var itemcontents: String

) {

}
