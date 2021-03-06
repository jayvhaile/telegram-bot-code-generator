package class_to_body_transformer

import domain.TelegramAPI
import domain.class_system.*

class KotlinTelegramAPIToBodyTransformer : TelegramAPIToBodyTransformer {
    override fun transform(api: TelegramAPI): List<BodyClass> {
        return api.types.map {
            BodyClass("${it.name}.kt", "/types", transformClass(it))
        }
    }

    private fun transformClass(clazz: Clazz): String {
        val type = when {
            clazz.isSealed -> "sealed class"
            clazz.isAbstract -> "abstract class"
            clazz.isInterface -> "interface"
            else -> "data class"
        }
        return """
            package generated.types
            
            ${getImports(clazz)}
            
            $type ${clazz.name}(${
            clazz.fields.joinToString(
                ",",
                transform = this::transformField
            )
        }) ${clazz.parentClazz?.let { ":${transformType(it)}" } ?: ""} 
            
        """.trimIndent()
    }

    private fun getImports(clazz: Clazz): String {
        return "import java.util.*"
    }

    private fun transformField(field: Field): String {
        var typeBody = transformType(field.type)
        if (!field.isRequired) typeBody = "Optional<$typeBody>"

        return "val ${field.name}:${typeBody}"
    }

    private fun transformType(type: Type): String {
        if (type == StringType) println("string")
        return when (type) {
            is StringType -> "String"
            is IntegerType -> "Int"
            is DoubleType -> "Double"
            is BooleanType -> "Boolean"
            is DateType -> "DateTime"
            is ArrayType -> "List<${transformType(type.arrayType)}>"
            is ObjectType -> type.name
            is EnumType -> type.name
            is UnionType -> type.name
        }
    }

}