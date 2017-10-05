package kz.qwertukg.generator.builders.model

import java.io.File
import kz.qwertukg.generator.*
import kz.qwertukg.generator.builders.base.WithContentAndPath

/**
 * Created by Daniil Rakhmatulin.
 */
class ModelFile(private val packageName: String) : WithContentAndPath {
    override val fileName = "models.kt"
    override val directoryName = packageName + File.separator + SYS_DIR
    override val content = StringBuilder()

    init {
        content.appendln("package $packageName.$SYS_DIR").appendln()
        content.appendln("import javax.persistence.*").appendln()
        content.appendln(watermark)
    }

    fun addModel(name: String, block: ModelClass.() -> Unit) {
        val builder = ModelClass(name).apply {
            content.appendln("data class ${name.capitalize()}(")
            block()
            addId()
            content.appendln(")")
        }

        content.appendln(builder.content)
    }
}

