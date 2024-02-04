package pl.touk.krush.realreferences

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(
        @Id
        @GeneratedValue
        val id: Long? = null,

        @Column
        val name: String,

        @ManyToOne
        @JoinColumn(name = "parent_id")
        val parent: Category?,

        @OneToMany(mappedBy = "parent")
        val children: List<Category> = emptyList()
)
