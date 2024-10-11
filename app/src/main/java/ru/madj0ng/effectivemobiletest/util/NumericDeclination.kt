package ru.madj0ng.effectivemobiletest.util

class NumericDeclination {
    fun feminine(number: Int) = convert(number, arrayOf("я", "й", "и"))

    fun masculine(number: Int): String = convert(number, arrayOf("", "", "а"))

    private fun convert(number: Int, arr: Array<String>): String {
        val mod: Int = (number % 100)
        val res = if (mod > 19) mod % 10 else mod
        return when (res) {
            1 -> arr[0]
            2, 3, 4 -> arr[2]
            else -> arr[1]
        }
    }
}