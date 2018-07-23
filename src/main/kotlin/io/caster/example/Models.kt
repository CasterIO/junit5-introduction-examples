package io.caster.example

data class Card(val suit: String, val value: String) {
    override fun toString() = "$value$suit"
}
