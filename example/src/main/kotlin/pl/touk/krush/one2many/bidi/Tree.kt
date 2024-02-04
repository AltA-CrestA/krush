package pl.touk.krush.one2many.bidi

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
data class Tree(

    @Id @GeneratedValue
    val id: Long? = null,

    val name: String,

    @OneToMany(mappedBy = "tree")
    val branches: List<Branch> = emptyList()
)

@Entity
data class Branch(

    @Id
    @GeneratedValue
    val id: Long? = null,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "my_tree_id")
    val tree: Tree? = null,

    @OneToMany(mappedBy = "branch")
    val leafs: List<Leaf> = emptyList()
)

@Entity
data class Leaf(

    @Id @GeneratedValue
    val id: Long? = null,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "branch_id")
    val branch: Branch? = null
)
