package pl.touk.krush.many2many

import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Embeddable
data class CharacterId(
    val id: Long,
    val season: Int
)

@Entity
data class Character(
    @EmbeddedId
    val id: CharacterId,

    val name: String
)

@Entity
data class Player(
    @Id @GeneratedValue
    val id: Long? = null,

    val name: String,

    @ManyToMany
    @JoinTable(name = "player_characters")
    val characters: List<Character>
)
