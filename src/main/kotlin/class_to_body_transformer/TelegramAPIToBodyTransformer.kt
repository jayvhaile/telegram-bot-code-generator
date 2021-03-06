package class_to_body_transformer

import domain.TelegramAPI
import domain.class_system.*

interface TelegramAPIToBodyTransformer {
    fun transform(api: TelegramAPI): List<BodyClass>
}

