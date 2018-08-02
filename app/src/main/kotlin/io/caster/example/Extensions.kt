package io.caster.example

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

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

fun <T : View> Activity.viewById(@IdRes res: Int): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}

fun Int.orNullIfLessThan(threshold: Int): Int? = if (this <= threshold) null else this
