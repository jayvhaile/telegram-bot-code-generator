package reader

interface TelegramAPIReader {
    fun read(): Map<String, Any>
}