package input_to_class_transformer

import domain.TelegramAPI

interface TelegramAPITransformer {
    fun transform(data: Map<String, Any>): TelegramAPI
}