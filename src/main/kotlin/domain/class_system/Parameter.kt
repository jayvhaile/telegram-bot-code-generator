package domain.class_system

data class Parameter(
    val name: String,
    val description: String,
    val type: Type,
    val isRequired: Boolean = false,
    val defaultValue: String? = null
)