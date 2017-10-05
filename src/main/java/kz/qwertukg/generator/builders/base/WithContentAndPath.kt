package kz.qwertukg.generator.builders.base

import java.io.File

/**
 * Created by Daniil Rakhmatulin.
 */
interface WithContentAndPath {
    val fileName: String
    val directoryName: String
    val content: StringBuilder

    fun save(destinationPath: String) {
        val path = destinationPath + File.separator + directoryName + File.separator + fileName
        File(path).apply {
            parentFile.mkdirs()
            createNewFile()
            writeText(content.toString())
        }

        println("-> [$path] saved!")
    }
}