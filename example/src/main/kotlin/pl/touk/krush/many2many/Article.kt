package pl.touk.krush.many2many

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "articles")
data class Article(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(name = "title")
    val title: String,

    @ManyToMany
    @JoinTable(name = "article_tags")
    val tags: List<Tag> = emptyList()
)

@Entity
@Table(name = "tags")
data class Tag(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(name = "name")
    val name: String
)
