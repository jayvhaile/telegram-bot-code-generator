package reader

import com.google.gson.Gson
import getAs
import java.io.FileReader

class FileTelegramAPIReader(apiFilePath: String) : TelegramAPIReader {
    private val fileReader = FileReader(apiFilePath)

    override fun read(): Map<String, Any> {
        return getAs(Gson().fromJson(fileReader.readText(), Map::class.java))
    }
}