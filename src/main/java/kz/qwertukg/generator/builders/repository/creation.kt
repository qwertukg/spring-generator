package kz.qwertukg.generator.builders.repository

import kz.qwertukg.generator.SYS_DIR

/**
 * Created by Daniil Rakhmatulin.
 */
fun createRepositories(packageName: String, block: RepositoryFile.() -> Unit) = RepositoryFile(packageName).apply {
    block()
    println("-> [$packageName.${SYS_DIR}.$fileName] generated!")
}