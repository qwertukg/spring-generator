package kz.qwertukg.generator.builders

import kz.qwertukg.generator.snake
import kz.qwertukg.generator.json.*

/**
 * Created by Daniil Rakhmatulin.
 */
class Type(val name: String, val default: Any, val annotations: List<String> = listOf())

fun Property.getType(modelName: String): Type {
    val possibleTypes = listOf(
            Type("Int", 0, listOf(Annotation.column(name))),
            Type("Long", 0L, listOf(Annotation.column(name))),
            Type("Double", 0.0, listOf(Annotation.column(name))),
            Type("String", "\"\"", listOf(Annotation.column(name)))
    )

    return possibleTypes.firstOrNull { it.name == type.capitalize() } ?: getRelationType(modelName)
}

private fun Property.getRelationType(modelName: String): Type {
    val typeName = type.capitalize()

    if (hasOne != null) return Type(typeName + "?", "null", listOf(Annotation.column(name + "_id"), Annotation.manyToOne()))
    if (hasMany != null) return Type(typeName + "?", "null", listOf(Annotation.joinColumn(modelName), Annotation.oneToMany()))
    if (manyMany != null) TODO("Not implemented yet")

    throw NoSuchElementException("Can't find type [$typeName]")
}