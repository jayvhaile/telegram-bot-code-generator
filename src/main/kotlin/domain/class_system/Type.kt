package domain.class_system

sealed class Type

object StringType : Type()

object IntegerType : Type()

object DoubleType : Type()

object BooleanType : Type()

object DateType : Type()

class ArrayType(val arrayType: Type) : Type()

class ObjectType(val name: String) : Type()

class EnumType(val name: String, val enums: List<String>) : Type()

class UnionType(val name: String, val types: List<Type>): Type()
