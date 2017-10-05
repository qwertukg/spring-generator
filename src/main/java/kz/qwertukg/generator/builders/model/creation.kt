package kz.qwertukg.generator.builders.model

import kz.qwertukg.generator.SYS_DIR

/**
 * Created by Daniil Rakhmatulin.
 */
fun createModels(packageName: String, block: ModelFile.() -> Unit) = ModelFile(packageName).apply {
    block()
    println("-> [$packageName.${SYS_DIR}.$fileName] generated!")
}