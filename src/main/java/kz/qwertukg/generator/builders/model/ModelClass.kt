package kz.qwertukg.generator.builders.model

import kz.qwertukg.generator.*
import kz.qwertukg.generator.builders.Type

/**
 * Created by Daniil Rakhmatulin.
 */
class ModelClass(private val modelName: String) {
    val content = StringBuilder()

    init {
        content.appendln("@Entity")
        content.appendln("@Table(name = \"${modelName.snake.plural}\")")
    }

    fun addProperty(name: String, type: Type) {
        type.annotations.forEach { content.appendln("\t\t$it") }
        content.appendln("\t\tvar $name: ${type.name} = ${type.default},").appendln()
    }

    fun addId() {
        content.appendln("\t\t@Id")
        content.appendln("\t\t@GeneratedValue(strategy = GenerationType.AUTO)")
        content.appendln("\t\tvar id: $ID_TYPE = 0")
    }
}