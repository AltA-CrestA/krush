package pl.touk.krush.one2many.uni

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Entity
data class Tree(

    @Id @GeneratedValue
    val id: Long? = null,

    val name: String,

    @OneToMany
    @JoinColumn(name = "my_tree_id")
    val branches: List<Branch> = emptyList()
)

@Entity
data class Branch(

    @Id
    @GeneratedValue
    val id: Long? = null,

    val name: String,

    @OneToMany
    @JoinColumn(name = "branch_id")
    val leafs: List<Leaf> = emptyList()
)

@Entity
data class Leaf(

    @Id @GeneratedValue
    val id: Long? = null,

    val name: String
)
