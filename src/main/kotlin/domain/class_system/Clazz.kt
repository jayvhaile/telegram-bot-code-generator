package domain.class_system

data class Clazz(
    val name: String,
    val description: String,
    val href: String,
    val plural: String = name,
    val fields: List<Field>,
    val methods: List<Function> = listOf(),
    val parentClazz: Type? = null,
    val interfaces: List<Type> = listOf(),
    val isInterface: Boolean = false,
    val isAbstract: Boolean = false,
    val isSealed: Boolean = false,
)

//a text representation of a class
data class BodyClass(val name: String, val relativePath: String, val body: String)