package pl.touk.krush.embeddable

import java.time.LocalDateTime
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity

@Embeddable
data class RecordId(
    @Column(name = "ID")
    val id: String,

    @Column(name = "TYPE")
    val type: String
) {
    companion object {
        fun from(id: String, type: String) = RecordId(id, type)
    }
}

@Entity
data class Record(

    @EmbeddedId
    val id: RecordId,

    val timestamp: LocalDateTime

)
