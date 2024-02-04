package pl.touk.krush.one2one

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
data class Item(
    @Id @GeneratedValue
    val id: Long? = null,
    val size: Int
)

@Entity
data class Box(
    @Id @GeneratedValue
    val id: Long? = null,

    @OneToOne(optional = true)
    @JoinColumn(name = "box_id")
    val item: Item? = null,
    // some other data maybe
)
