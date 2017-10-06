package kz.qwertukg.generator.json

/**
* Created by Daniil Rakhmatulin.
*/
data class Project(
        val name: String = "",
        val models: List<Model> = listOf(),
        val config: List<ConfigItem> = listOf()
)

data class Model(
        val name: String = "",
        val properties: List<Property> = listOf()
)

data class Property(
        val name: String = "",
        val type: String = "",
        val default: String = "",
        val hasOne: String? = null,
        val hasMany: String? = null,
        val manyMany: String? = null
)

data class ConfigItem(
        val name: String = "",
        val value: String = ""
)