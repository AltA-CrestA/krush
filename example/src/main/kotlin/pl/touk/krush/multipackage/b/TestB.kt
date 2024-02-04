package pl.touk.krush.multipackage.b

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "test_b")
data class TestB(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column val text: String
)
