import class_to_body_transformer.TelegramAPIToBodyTransformer
import input_to_class_transformer.TelegramAPITransformer
import reader.TelegramAPIReader
import writer.TelegramAPIWriter

class Orchestrator(
    private val reader: TelegramAPIReader,
    private val apiTransformer: TelegramAPITransformer,
    private val apiToBodyTransformer: TelegramAPIToBodyTransformer,
    private val writer: TelegramAPIWriter,
) {

    fun execute() {
        apiToBodyTransformer.transform(apiTransformer.transform(reader.read())).forEach(writer::write);
    }
}