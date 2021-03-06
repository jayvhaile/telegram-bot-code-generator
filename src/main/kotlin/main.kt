import class_to_body_transformer.KotlinTelegramAPIToBodyTransformer
import input_to_class_transformer.DefaultTelegramAPITransformer
import reader.FileTelegramAPIReader
import writer.FileTelegramAPIWriter


fun main() {
    Orchestrator(
        FileTelegramAPIReader("api.json"),
        DefaultTelegramAPITransformer(),
        KotlinTelegramAPIToBodyTransformer(),
        FileTelegramAPIWriter("C:\\Users\\pc\\Documents\\Project_Files\\AskEt\\tools\\KBot\\src\\main\\kotlin\\generated")
    ).execute()
}