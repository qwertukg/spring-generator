package kz.qwertukg.generator.builders.config

import kz.qwertukg.generator.builders.controller.ControllerFile

/**
 * Created by Daniil Rakhmatulin.
 */
fun createConfig(packageName: String, block: ConfigFile.() -> Unit) = ConfigFile(packageName).apply {
    block()
    println("-> [resources.$fileName] generated!")
}