package com.isamadrid90.fizzbuzz

class Main(private val translator: FizzBuzzTranslator, private val printer: Printer) {
    fun execute(
        first: Int,
        last: Int,
    ) {
        val values = translator.execute(first, last)
        var position = 0
        while (position < values.size) {
            printer.execute(values[position])
            position++
        }
    }
}
