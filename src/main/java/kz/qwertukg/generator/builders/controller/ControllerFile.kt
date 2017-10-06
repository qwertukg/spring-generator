package kz.qwertukg.generator.builders.controller

import java.io.File
import kz.qwertukg.generator.*
import kz.qwertukg.generator.builders.base.WithContentAndPath

/**
 * Created by Daniil Rakhmatulin.
 */
class ControllerFile(private val packageName: String) : WithContentAndPath {
    override val fileName = "controllers.kt"
    override val directoryName = packageName.toPath + File.separator + SYS_DIR
    override val content = StringBuilder()

    init {
        content.appendln("package $packageName.$SYS_DIR").appendln()
        content.appendln("import org.springframework.web.bind.annotation.*").appendln()
        content.appendln(watermark)
    }

    fun addController(name: String, block: ControllerClass.() -> Unit) {
        val builder = ControllerClass(name).apply {
            content.appendln("class ${name.plural.capitalize()}Controller(val repository: ${name.capitalize()}Repository) {")
            addIndex()
            addCreate()
            addRead()
            addUpdate()
            addDelete()
            block()
            content.appendln("}")
        }

        content.appendln(builder.content)
    }
}