package kz.qwertukg.generator.builders.application

import kz.qwertukg.generator.builders.config.ConfigFile

/**
 * Created by Daniil Rakhmatulin.
 */
fun createApplication(packageName: String, block: ApplicationFile.() -> Unit) = ApplicationFile(packageName).apply {
    block()
    println("-> [$packageName.$fileName] generated!")
}