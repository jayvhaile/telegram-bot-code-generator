package writer

import domain.class_system.BodyClass
import java.io.File
import java.io.FileWriter

interface TelegramAPIWriter {
    fun write(bodyClass: BodyClass)
}

class FileTelegramAPIWriter(private val outputPath: String) : TelegramAPIWriter {
    override fun write(bodyClass: BodyClass) {
        val file=File(File(outputPath, bodyClass.relativePath).apply { mkdirs() },bodyClass.name)
        val fileWriter = FileWriter(file.path)
        fileWriter.write(bodyClass.body)
        fileWriter.flush()
        fileWriter.close()
    }
}