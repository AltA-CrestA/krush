package pl.touk.example

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customers")
data class Customer(

        @Id @GeneratedValue
        val id: Long?,

        @Column(name = "name")
        val name: String
)
