package pl.touk.krush.companion

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class JobId(val id: Long = 0) {

    override fun toString(): String = id.toString()

    companion object : AttributeConverter<JobId, Long> {
        override fun convertToDatabaseColumn(attribute: JobId): Long = attribute.id
        override fun convertToEntityAttribute(dbData: Long): JobId = JobId(dbData)
    }
}

@Entity
data class Resource(
    @Id val id: Long = 0,

    @Convert(converter = JobId.Companion::class)
    val jobId: JobId = JobId()
)
