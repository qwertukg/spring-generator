package kz.qwertukg.generator.builders.controller

import kz.qwertukg.generator.*

/**
 * Created by Daniil Rakhmatulin.
 */
class ControllerClass(private val controllerName: String) {
    val content = StringBuilder()

    init {
        content.appendln("@RestController")
        content.appendln("@RequestMapping(\"${controllerName.route.plural}\")")
    }

    fun addIndex() {
        content.appendln("\t@GetMapping")
        content.appendln("\tfun index() = repository.findAll()").appendln()
    }

    fun addCreate() {
        content.appendln("\t@PostMapping")
        content.appendln("\tfun create(@RequestBody model: ${controllerName.capitalize()}) = repository.save(model)").appendln()
    }

    fun addRead() {
        content.appendln("\t@GetMapping(\"{id}\")")
        content.appendln("\tfun read(@PathVariable id: $ID_TYPE) = repository.findOne(id)").appendln()
    }

    fun addUpdate() {
        content.appendln("\t@PutMapping(\"{id}\")")
        content.appendln("\tfun update(@PathVariable id: $ID_TYPE, @RequestBody model: ${controllerName.capitalize()}) = repository.save(model.apply { this.id = id })").appendln()
    }

    fun addDelete() {
        content.appendln("\t@DeleteMapping(\"{id}\")")
        content.appendln("\tfun delete(@PathVariable id: $ID_TYPE) = repository.delete(id)")
    }
}