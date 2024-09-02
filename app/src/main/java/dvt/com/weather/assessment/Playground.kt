package dvt.com.weather.assessment


inline fun <reified T> displayValue(value: T) {
    println(value)
    println("Type of T: ${T::class.java}")

}

fun main() {
    displayValue<String>("Generics")
    displayValue<Int>(10)
}