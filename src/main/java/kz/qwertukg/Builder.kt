package kz.qwertukg

import kz.qwertukg.generator.builders.application.createApplication
import kz.qwertukg.generator.json.Project
import kz.qwertukg.generator.builders.getType
import kz.qwertukg.generator.builders.model.createModels
import kz.qwertukg.generator.builders.base.WithContentAndPath
import kz.qwertukg.generator.builders.config.createConfig
import kz.qwertukg.generator.builders.controller.createControllers
import kz.qwertukg.generator.builders.repository.createRepositories

/**
 * Created by Daniil Rakhmatulin.
 */
class Builder(private val project: Project, private val print: Boolean = false) {
    private val list = mutableListOf<WithContentAndPath>()

    fun build(): List<WithContentAndPath> {
        // generate models
        list.add(createModels(project.name) {
            project.models.forEach { model ->
                addModel(model.name) {
                    model.properties.forEach { property ->
                        addProperty(property.name, property.getType(project.models, model.name))
                    }
                }
            }
        })

        // generate controllers
        list.add(createControllers(project.name) {
            project.models.forEach { model ->
                addController(model.name) {  }
            }
        })

        // generate repositories
        list.add(createRepositories(project.name) {
            project.models.forEach { model ->
                addRepository(model.name) {  }
            }
        })

        // generate application
        list.add(createApplication(project.name) {  })

        // generate application.properties
        list.add(createConfig(project.name) {
            project.config.forEach { item ->
                addItem(item.name, item.value) {  }
            }
        })

        if (print) list.forEach { pp(it) }

        return list
    }

    fun pp(builder: WithContentAndPath) {
        println("------------------------------------------")
        println(builder.fileName)
        println("------------------------------------------")
        println(builder.content)
    }
}