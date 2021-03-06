package input_to_class_transformer

import domain.TelegramAPI
import domain.class_system.*
import domain.class_system.Function
import getAs

class DefaultTelegramAPITransformer : TelegramAPITransformer {


    private fun transformMethods(methods: Map<String, Any>): List<Function> {
        return listOf()
    }


    private fun pluralize(word: String) = word

    private fun transformType(type: Map<String, Any>): Clazz {
        return Clazz(
            name = type["name"] as String,
            description = (type["description"] as List<*>).joinToString(),
            href = type["href"] as String,
            plural = pluralize(type["name"] as String),
            fields = if (type["fields"] == null) listOf() else (type["fields"] as List<*>).map { transformField(getAs(it)) },
            isSealed = type["subtypes"] != null,
            parentClazz = type["subtype_of"]?.let { l ->
                (l as? List<*>)?.getOrNull(0)?.let { it as? String }?.let { ObjectType(it) }
            }
        )
    }

    private fun getType(type: String): Type {
        if (type == "String") println("asdasd")
        return when {
            type == "Integer" -> IntegerType
            type == "String" -> StringType
            type == "Boolean" -> BooleanType
            type.startsWith("Array") -> ArrayType(getType(type.split(" ").last()))
            else -> ObjectType(type)
        }
    }

    private fun transformField(field: Map<String, Any>): Field {
        return Field(
            name = field["name"] as String,
            description = field["description"] as String,
            isRequired = field["required"] as Boolean,
            type = (field["types"] as List<*>).let {
                if (it.size == 1) getType(it[0] as String)
                else UnionType(field["name"] as String, it.map { a -> getType(a as String) })
            }
        )
    }


    override fun transform(data: Map<String, Any>): TelegramAPI {
        val types = getAs<Map<String, Map<String, Any>>>(data["types"]).values.map(this::transformType)
        val methods = getAs<Map<String, Any>>(data["methods"])
        return TelegramAPI(types, listOf())
    }
}