package kz.qwertukg.generator.builders.controller

import kz.qwertukg.generator.SYS_DIR

/**
 * Created by Daniil Rakhmatulin.
 */
fun createControllers(packageName: String, block: ControllerFile.() -> Unit) = ControllerFile(packageName).apply {
    block()
    println("-> [$packageName.$SYS_DIR.$fileName] generated!")
}