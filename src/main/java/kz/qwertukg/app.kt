package kz.qwertukg

import java.io.File
import com.google.gson.Gson
import kz.qwertukg.generator.*
import kz.qwertukg.generator.json.Project

/**
 * Created by Daniil Rakhmatulin
 */
fun main(args: Array<String>) {
    println(logo)

    print("Input path of json config: ")
    val configPath = readLine()!!
    val project = Gson().fromJson(File(configPath).readText(), Project::class.java)!!

    val pp = args.isNotEmpty() && args[0] == "-p"
    val results = Builder(project, true).build()

    print("Input destination folder path: ")
    val destinationPath = readLine()!!

    results.forEach { it.save(destinationPath) }
}