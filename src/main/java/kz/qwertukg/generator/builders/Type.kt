package kz.qwertukg.generator.builders

import kz.qwertukg.generator.snake
import kz.qwertukg.generator.json.*

/**
 * Created by Daniil Rakhmatulin.
 */
class Type(val name: String, val default: Any, val annotations: List<String> = listOf())

fun Property.getType(models: List<Model>, currentModelName: String): Type {
    val columnAnnotation = "@Column(name = \"${name.snake}\")"
    val manyToOneAnnotation = "@ManyToOne(cascade = arrayOf(CascadeType.ALL))"
    val jsonColumnAnnotation = "@JoinColumn(name = \"${currentModelName.snake}_id\")"
    val oneToManyAnnotation = "@OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)"

    val possibleTypes = mutableListOf(
            Type("Int", 0, mutableListOf(columnAnnotation)),
            Type("Long", 0, mutableListOf(columnAnnotation)),
            Type("Double", 0.0, mutableListOf(columnAnnotation)),
            Type("String", "\"\"", mutableListOf(columnAnnotation))
    )

    val typeName = type.capitalize()

    if (!possibleTypes.any { it.name == typeName }) {
        return if (models.any { it.name == typeName })
            Type(typeName + "?", "null", listOf(
                    manyToOneAnnotation
            ))
        else if (models.any { it.name in typeName } && "<" in typeName && ">" in typeName)
            Type(typeName + "?", "null", listOf(
                    jsonColumnAnnotation,
                    oneToManyAnnotation
            ))
        else throw NoSuchElementException("Can't find related model or Type with name [$typeName]")
    }

    return possibleTypes.first { it.name == typeName }
}