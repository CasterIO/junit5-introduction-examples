package io.caster.example

fun readLine(message: String? = null): String? {
    message?.let {
        println(it)
        print("> ")
    }
    val input = kotlin.io.readLine()
    return if (input.isNullOrEmpty()) {
        null
    } else {
        input
    }
}
