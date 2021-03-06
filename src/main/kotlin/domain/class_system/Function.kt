package domain.class_system

data class Function(
    val name: String,
    val description: String,
    val returnType: Type,
    val parameters: List<Parameter>
)