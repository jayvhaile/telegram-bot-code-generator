package domain

import domain.class_system.Clazz
import domain.class_system.Function


data class TelegramAPI(val types: List<Clazz>, val method: List<Function>)

