package pl.touk.krush.meta

import javax.lang.model.element.VariableElement
import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinColumns

fun VariableElement.mappingOverrides(): List<AttributeOverride> {
    return (this.getAnnotation(AttributeOverrides::class.java)?.value?.toList() ?: emptyList()) +
            (this.getAnnotation(AttributeOverride::class.java)?.let { listOf(it) } ?: emptyList())
}

fun VariableElement.joinColumns(): List<JoinColumn> {
    return (this.getAnnotation(JoinColumns::class.java)?.value?.toList() ?: emptyList()) +
            (this.getAnnotation(JoinColumn::class.java)?.let { listOf(it) } ?: emptyList())
}
