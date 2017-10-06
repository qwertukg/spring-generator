package kz.qwertukg.generator.builders

import kz.qwertukg.generator.snake

object Annotation {
    fun column(name: String) = "@Column(name = \"${name.snake}\")"
    fun joinColumn(modelName: String) = "@JoinColumn(name = \"${modelName.snake}_id\")"
    fun manyToOne() = "@ManyToOne(cascade = arrayOf(CascadeType.ALL))"
    fun oneToMany() = "@OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)"
}