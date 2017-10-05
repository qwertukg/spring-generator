package kz.qwertukg.generator.builders.repository

import kz.qwertukg.generator.ID_TYPE

/**
 * Created by Daniil Rakhmatulin.
 */
class RepositoryClass(private val repositoryName: String) {
    val content = StringBuilder()

    init {
        content.appendln("interface ${repositoryName.capitalize()}Repository : CrudRepository<${repositoryName.capitalize()}, $ID_TYPE>")
    }
}