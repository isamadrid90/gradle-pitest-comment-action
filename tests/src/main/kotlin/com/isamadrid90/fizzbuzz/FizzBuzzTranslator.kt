package com.isamadrid90.fizzbuzz

class FizzBuzzTranslator(private val counter: Counter) {
    fun execute(
        start: Int,
        end: Int,
    ): List<String> {
        val list = mutableListOf<String>()
        counter.count(start, end).map {
            when {
                it.isMultipleOf(15) -> list.add(multipleOf15Translation())
                it.isMultipleOf(3) -> list.add(multipleOf3Translation())
                it.isMultipleOf(5) -> list.add(multipleOf5Translation())
                else -> list.add("$it")
            }
        }
        return list
    }

    private fun multipleOf5Translation() = "Buzz"

    private fun multipleOf3Translation() = "Fizz"

    private fun multipleOf15Translation() = "FizzBuzz"
}

private fun Int.isMultipleOf(multiple: Int): Boolean {
    return this % multiple == 0
}
