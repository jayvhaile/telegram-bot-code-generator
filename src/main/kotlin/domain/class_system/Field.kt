package domain.class_system

data class Field(
    val name: String,
    val description: String,
    val type: Type,
    val isRequired: Boolean = true,
    val defaultValue: String? = null
)