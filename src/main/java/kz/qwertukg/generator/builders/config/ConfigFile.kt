package kz.qwertukg.generator.builders.config

import kz.qwertukg.generator.builders.base.WithContentAndPath
import kz.qwertukg.generator.route

/**
 * Created by Daniil Rakhmatulin.
 */
class ConfigFile(private val projectName: String) : WithContentAndPath {
    override val fileName = "application-$projectName.properties"
    override val directoryName = "resources"
    override val content = StringBuilder()

    private val preloadItems = mapOf(
            "spring.jackson.property-naming-strategy" to "CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES",
            "spring.jpa.hibernate.ddl-auto" to "create",
            "spring.jpa.show-sql" to "false"
    )

    init {
        preloadItems.forEach { name, value -> content.appendln("$name = $value") }
        content.appendln()
    }

    fun addItem(name: String, value: String, block: ConfigFile.() -> Unit) {
        if (!preloadItems.containsKey(name)) content.appendln("$name = $value")
        block()
    }
}