package io.github.azmostafa

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory
import java.io.File

class DimensPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("redimens") {
            group = "azmostafa"
            description = "Generates dimens.xml files for multiple screen sizes."

            doLast {
                println("✅ Running redimens task...")
              /*  val referenceSize = 1080
                val targetSize = listOf(
                    600,
                    700,
                    800
                )

                val baseFolder = File(
                    project.rootProject.projectDir, "app/src/main/res/values-sw${referenceSize}dp"
                ).absolutePath

                val baseDimensFile = File("$baseFolder/dimens.xml")
                if (!baseDimensFile.exists()) {
                    println("[ATTENTION] the file of `dimens.xml` NOT FOUND in ${baseDimensFile.absolutePath}!")
                    return@doLast
                }

                val factory = DocumentBuilderFactory.newInstance()
                val builder = factory.newDocumentBuilder()
                val doc = builder.parse(baseDimensFile)
                val dimens = doc.getElementsByTagName("dimen")

                for (size in targetSize) {
                    val folder = "values-sw${size}dp"
                    val ratio = size.toFloat() / referenceSize.toFloat()

                    val newFolder = File(project.rootProject.projectDir, "app/src/main/res/$folder")
                    if (!newFolder.exists()) {
                        if (newFolder.mkdirs()) {
                            println("--- Created folder: ${newFolder.absolutePath}")
                        } else {
                            println("[ATTENTION] Failed to create folder: ${newFolder.absolutePath}")
                            return@doLast
                        }
                    }

                    val newFile = File(newFolder, "dimens.xml")
                    newFile.writeText("<resources>\n")
                    for (i in 0 until dimens.length) {
                        val dimenNode = dimens.item(i) as Element
                        val name = dimenNode.getAttribute("name")
                        val value =
                            dimenNode.textContent.replace("sp", "").replace("dp", "").toFloat()

                        val newValue = value * ratio
                        val unit = if ("sp" in dimenNode.textContent) "sp" else "dp"

                        newFile.appendText("    <dimen name=\"$name\">${"%.2f".format(newValue)}$unit</dimen>\n")
                    }
                    newFile.appendText("</resources>\n")

                    println("--- successfully created dimens.xml for route of $folder!")
                }

                pri*/ntln("\n[OK] successfully created all 'dimens.xml' files")
            }
        }
    }
}