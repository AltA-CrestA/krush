package pl.touk.krush.env

import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import com.squareup.kotlinpoet.metadata.toKmClass
import pl.touk.krush.meta.toTypeElement
import pl.touk.krush.meta.toVariableElement
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import jakarta.persistence.*

data class TypeEnvironment(
    val typeUtils: Types,
    val elementUtils: Elements
) {
    fun isSubType(type: TypeMirror, qualifiedName: CharSequence) =
            typeUtils.isSubtype(type, elementUtils.getTypeElement(qualifiedName).asType())

    fun isSameType(type: TypeMirror, qualifiedName: CharSequence) =
            typeUtils.isSameType(type, elementUtils.getTypeElement(qualifiedName).asType())
}

data class AnnotationEnvironment(
    val entities: List<TypeElement>,
    val ids: List<VariableElement>,
    val embeddedIds: List<VariableElement> = emptyList(),
    val columns: List<VariableElement>,
    val oneToMany: List<VariableElement>,
    val manyToOne: List<VariableElement>,
    val manyToMany: List<VariableElement>,
    val oneToOne: List<VariableElement>,
    val embedded: List<VariableElement> = emptyList(),
    val embeddedColumn: List<VariableElement> = emptyList()
)

fun Element.enclosingTypeElement() = this.enclosingElement.toTypeElement()

@KotlinPoetMetadataPreview
class EnvironmentBuilder(private val roundEnv: RoundEnvironment, private val processingEnv: ProcessingEnvironment) {

    fun buildAnnotationEnv(): AnnotationEnvironment {
        val entities = roundEnv.getElementsAnnotatedWith(Entity::class.java).toTypeElements()
        val ids = roundEnv.getElementsAnnotatedWith(Id::class.java).toVariableElements()
        val embeddedIds = roundEnv.getElementsAnnotatedWith(EmbeddedId::class.java).toVariableElements()
        val oneToMany = roundEnv.getElementsAnnotatedWith(OneToMany::class.java).toVariableElements()
        val manyToOne = roundEnv.getElementsAnnotatedWith(ManyToOne::class.java).toVariableElements()
        val manyToMany = roundEnv.getElementsAnnotatedWith(ManyToMany::class.java).toVariableElements()
        val oneToOne = roundEnv.getElementsAnnotatedWith(OneToOne::class.java).toVariableElements()
        val columns = (roundEnv.rootElements.asSequence().flatMap(this::toColumnElements)
            .minus(ids + embeddedIds + oneToOne + oneToMany + manyToOne + manyToMany)).toList()
        val embedded = roundEnv.getElementsAnnotatedWith(Embedded::class.java).toVariableElements()
        val embeddedColumn = roundEnv.getElementsAnnotatedWith(Embeddable::class.java).map(this::toEmbeddedElements).flatten().toList()

        return AnnotationEnvironment(entities = entities, ids = ids, embeddedIds = embeddedIds, columns = columns, oneToMany = oneToMany,
                manyToOne = manyToOne, manyToMany = manyToMany, oneToOne = oneToOne, embedded = embedded,
                embeddedColumn = embeddedColumn)
    }

    private fun toColumnElements(entity: Element) =
        entity.enclosedElements.filter(this::isColumn).map(Element::toVariableElement)

    private fun Element.isCompanionObject(): Boolean =
        simpleName.toString() == "Companion" &&
        toVariableElement().toTypeElement().toKmClass().companionObject.isNullOrBlank()

    private fun isColumn(element: Element): Boolean {
        return element.kind == ElementKind.FIELD &&
                element.isCompanionObject().not() &&
                element.enclosingElement.getAnnotation(Entity::class.java) != null &&
                element.getAnnotation(Transient::class.java) == null && element.getAnnotation(Embedded::class.java) == null
    }

    private fun toEmbeddedElements(embeddable: Element): List<VariableElement> {
        val enclosingKmClass = embeddable.toTypeElement().toKmClass()
        val propertyNames = enclosingKmClass.properties.map { it.name }
        return (embeddable.asType() as DeclaredType).asElement().enclosedElements
            .filter { propertyNames.contains(it.simpleName.toString()) }
            .filter(this::isEmbedded)
            .map(Element::toVariableElement)
    }

    private fun isEmbedded(element: Element) = element.kind == ElementKind.FIELD &&
            element.enclosingElement.getAnnotation(Embeddable::class.java) != null &&
            element.getAnnotation(Transient::class.java) == null && element.getAnnotation(Embedded::class.java) == null

    fun buildTypeEnv() = TypeEnvironment(processingEnv.typeUtils, processingEnv.elementUtils)

    private fun Collection<Element>.toTypeElements() = this.map(Element::toTypeElement)
    private fun Collection<Element>.toVariableElements() = this.map(Element::toVariableElement)

}
