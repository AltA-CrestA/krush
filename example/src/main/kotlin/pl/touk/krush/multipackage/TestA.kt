package pl.touk.krush.multipackage

import pl.touk.krush.multipackage.b.TestB
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "test_a")
data class TestA(
    @Id @GeneratedValue
    val id: Long? = null,

    @OneToMany
    @JoinColumn(name = "testA_id")
    val b: List<TestB> = emptyList()
)
