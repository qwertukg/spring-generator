package kz.qwertukg

import java.io.File
import com.google.gson.Gson
import kz.qwertukg.generator.json.Project
import kz.qwertukg.generator.builders.getType
import kz.qwertukg.generator.builders.model.createModels
import kz.qwertukg.generator.builders.config.createConfig
import kz.qwertukg.generator.builders.base.WithContentAndPath
import kz.qwertukg.generator.builders.controller.createControllers
import kz.qwertukg.generator.builders.application.createApplication
import kz.qwertukg.generator.builders.repository.createRepositories

/**
 * Created by Daniil Rakhmatulin.
 */
object Builder {
    private val list = mutableListOf<WithContentAndPath>()

    fun build(project: Project, print: Boolean = false): List<WithContentAndPath> {
        // generate models
        list.add(createModels(project.packageName) {
            project.models.forEach { model ->
                addModel(model.name) {
                    model.properties.forEach { property ->
                        addProperty(property.name, property.getType(model.name))
                    }
                }
            }
        })

        // generate controllers
        list.add(createControllers(project.packageName) {
            project.models.forEach { model ->
                addController(model.name) {  }
            }
        })

        // generate repositories
        list.add(createRepositories(project.packageName) {
            project.models.forEach { model ->
                addRepository(model.name) {  }
            }
        })

        // generate application
        list.add(createApplication(project.packageName) {  })

        // generate application.properties
        list.add(createConfig(project.packageName) {
            project.config.forEach { item ->
                addItem(item.name, item.value) {  }
            }
        })

        if (print) list.forEach { pp(it) }

        return list
    }

    fun generate(configPath: String, destinationPath: String, print: Boolean = false) {
        val project = Gson().fromJson(File(configPath).readText(), Project::class.java)!!
        val results = build(project, print)

        results.forEach { it.save(destinationPath) }
    }

    private fun pp(builder: WithContentAndPath) {
        println("------------------------------------------")
        println(builder.fileName)
        println("------------------------------------------")
        println(builder.content)
    }
}