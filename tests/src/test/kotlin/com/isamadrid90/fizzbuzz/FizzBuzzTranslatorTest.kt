package com.isamadrid90.fizzbuzz

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class FizzBuzzTranslatorTest {
    private lateinit var counter: Counter
    private lateinit var fizzBuzzTranslator: FizzBuzzTranslator

    @BeforeEach
    internal fun setUp() {
        counter = mockk()
        fizzBuzzTranslator = FizzBuzzTranslator(counter)
    }

    @Test
    fun `should return Fizz when number is multiple of 3`() {
        every { counter.count(any(), any()) } returns IntRange(3, 3)

        val response = fizzBuzzTranslator.execute(3, 3)

        assertEquals(listOf("Fizz"), response)
    }

    @Test
    fun `should return Buzz when number is multiple of 5`() {
        every { counter.count(any(), any()) } returns IntRange(5, 5)

        val response = fizzBuzzTranslator.execute(5, 5)

        assertEquals(listOf("Buzz"), response)
    }

    @Test
    fun `should return FizzBuzz when number is multiple of 3 and 5`() {
        every { counter.count(any(), any()) } returns IntRange(15, 15)

        val response = fizzBuzzTranslator.execute(15, 15)

        assertEquals(listOf("FizzBuzz"), response)
    }

    @Test
    fun `should return Fizz, Buzz,FizzBuzz or number with a complete range`() {
        every { counter.count(any(), any()) } returns IntRange(1, 100)

        val response = fizzBuzzTranslator.execute(1, 100)

        assertEquals(
            listOf(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz", "16", "17",
                "Fizz", "19", "Buzz", "Fizz", "22", "23", "Fizz", "Buzz", "26", "Fizz", "28", "29", "FizzBuzz", "31", "32",
                "Fizz", "34", "Buzz", "Fizz", "37", "38", "Fizz", "Buzz", "41", "Fizz", "43", "44", "FizzBuzz", "46", "47",
                "Fizz", "49", "Buzz", "Fizz", "52", "53", "Fizz", "Buzz", "56", "Fizz", "58", "59", "FizzBuzz", "61", "62",
                "Fizz", "64", "Buzz", "Fizz", "67", "68", "Fizz", "Buzz", "71", "Fizz", "73", "74", "FizzBuzz", "76", "77",
                "Fizz", "79", "Buzz", "Fizz", "82", "83", "Fizz", "Buzz", "86", "Fizz", "88", "89", "FizzBuzz", "91", "92",
                "Fizz", "94", "Buzz", "Fizz", "97", "98", "Fizz", "Buzz",
            ),
            response,
        )
    }
}
