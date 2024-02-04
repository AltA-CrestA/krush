package pl.touk.krush.enumtype

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.ORDINAL
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Message(
    @Id @GeneratedValue
    val id: Long? = null,

    @Enumerated(STRING)
    val status: MessageStatus,

    @Enumerated(ORDINAL)
    val previousStatus: MessageStatus,

    @Embedded
    val info: MessageInfo
)

@Embeddable
data class MessageInfo(
    @Enumerated(STRING)
    val contentType: ContentType,

    @Enumerated(ORDINAL)
    val priority: MessagePriority
)

enum class MessageStatus {
    NEW, PENDING, RECEIVED
}

enum class MessagePriority(val priority: Int) {
    LOW(0), HIGH(999)
}

enum class ContentType {
    JSON, XML
}
