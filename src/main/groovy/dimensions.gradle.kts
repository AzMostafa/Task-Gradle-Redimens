import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory
import java.io.File

tasks.register("redimens") {
    doLast {
        // ---> SET the number of base file dp (like 360 for sw360dp):
        val referenceSize = 1080
        val targetSize = listOf(
            600,
            700,
            800,
        )

        // ---> creat the baseFolder Route:
        val baseFolder = File(
            rootProject.projectDir, "app/src/main/res/values-sw${referenceSize}dp"
        ).absolutePath

        // ---> check the file of dimens.xml is exist in the base folder:
        val baseDimensFile = File("$baseFolder/dimens.xml")
        if (!baseDimensFile.exists()) {
            println("[ATTENTION] the file of `dimens.xml` NOT FOUND in ${baseDimensFile.absolutePath}!")
            return@doLast
        }

        // ---> parse the reference file of xml:
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(baseDimensFile)
        val dimens = doc.getElementsByTagName("dimen")

        // ---> create the ratio number of dimens values for each target size:
        for (size in targetSize) {
            // ---> set the init values:
            val folder = "values-sw${size}dp"
            val ratio = size.toFloat() / referenceSize.toFloat()

            // ---> check the folder of target is exist or not:
            val newFolder = File(rootProject.projectDir, "app/src/main/res/$folder")
            if (!newFolder.exists()) {
                val created = newFolder.mkdirs()
                if (created) {
                    println("--- Created folder: ${newFolder.absolutePath}")
                } else {
                    println("[ATTENTION] Failed to create folder: ${newFolder.absolutePath}")
                    return@doLast
                }
            }

            // ---> Creat the file of dimens.xml for each target folder:
            val newFile = File(newFolder, "dimens.xml")
            newFile.writeText("<resources>\n")
            for (i in 0 until dimens.length) {
                val dimenNode = dimens.item(i) as Element
                val name = dimenNode.getAttribute("name")
                val value = dimenNode.textContent.replace("sp", "").replace("dp", "").toFloat()

                val newValue = value * ratio
                val unit = if ("sp" in dimenNode.textContent) "sp" else "dp"

                newFile.appendText("    <dimen name=\"$name\">${"%.2f".format(newValue)}$unit</dimen>\n")
            }
            newFile.appendText("</resources>\n")

            println("--- successfully created dimens.xml for route of $folder!")
        }

        println("\n[OK] successfully created all 'dimens.xml' files")
    }
}
