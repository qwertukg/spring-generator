package kz.qwertukg.generator.builders.repository

import java.io.File
import kz.qwertukg.generator.*
import kz.qwertukg.generator.builders.base.WithContentAndPath

/**
 * Created by Daniil Rakhmatulin.
 */
class RepositoryFile(private val packageName: String) : WithContentAndPath {
    override val fileName = "repositories.kt"
    override val directoryName = packageName.toPath + File.separator + SYS_DIR
    override val content = StringBuilder()

    init {
        content.appendln("package $packageName.$SYS_DIR").appendln()
        content.appendln("import org.springframework.data.repository.*").appendln()
        content.appendln(watermark)
    }

    fun addRepository(name: String, block: RepositoryClass.() -> Unit) {
        val builder = RepositoryClass(name).apply {
            block()
        }

        content.appendln(builder.content)
    }
}