package pl.touk.krush

import java.time.LocalDate
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "books")
data class Book(
    @Id @GeneratedValue
    val id: Int? = null,
    @Column(name = "ISBN")
    val isbn: String,
    val author: String,
    val title: String,
    val publishDate: LocalDate
)
