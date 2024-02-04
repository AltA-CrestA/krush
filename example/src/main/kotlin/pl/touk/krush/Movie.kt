package pl.touk.krush

import kotlinx.serialization.Serializable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "books")
@Serializable
data class Movie(
    @Id @GeneratedValue
    val id: Int? = null,

    val title: String
)
