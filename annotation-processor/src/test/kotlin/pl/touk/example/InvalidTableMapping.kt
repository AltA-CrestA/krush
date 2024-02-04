package pl.touk.example

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class InvalidClassEntity(

        @Id @GeneratedValue
        val id: Long?,

        val prop: String
)

@Entity
data class IdNotPresentEntity(
        val prop: String
)

@Entity
data class IdTypeUnsupportedEntity(
        @Id @GeneratedValue
        val id: Float?
)

@Entity
data class PropertyTypeUnsupportedEntity(
        @Id @GeneratedValue
        val id: Long?,

        val prop: Pair<String, String>
)
