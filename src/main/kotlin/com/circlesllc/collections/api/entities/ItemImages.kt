package com.circlesllc.collections.api.entities

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.type.BinaryType

import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name="bytea", typeClass = BinaryType::class)
data class ItemImages(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @Column
        val name: String,
        val itemImage: BinaryType
) {

}
