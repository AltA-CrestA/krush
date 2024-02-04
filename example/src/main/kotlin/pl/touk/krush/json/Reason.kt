package pl.touk.krush.json

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "REASONS")
data class Reason(
    @GeneratedValue
    @Id
    val id: Long? = null,

    @Column(name = "REASON")
    val reason: String,

    @Column(name = "DETAILS", columnDefinition = "jsonb")
    val details: String
)
