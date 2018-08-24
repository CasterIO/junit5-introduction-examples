package de.mannodermaus.example.common

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

fun Int.orNullIfBelow(barrier: Int) = if (this <= barrier) null else this
