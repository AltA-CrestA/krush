package pl.touk.krush.types

import java.math.BigDecimal
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Line(

    @Id @GeneratedValue
    val id: Long? = null,

    val x1: Long,

    val y1: Int,

    val z1: Short,

    val x2: Float,

    val y2: Double,

    @Column(precision = 6, scale = 3)
    val z2: BigDecimal
)
