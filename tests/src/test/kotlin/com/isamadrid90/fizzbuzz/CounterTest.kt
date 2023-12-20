package com.isamadrid90.fizzbuzz

import kotlin.test.Test
import kotlin.test.assertEquals

class CounterTest {
    @Test
    fun `should return numbers from 1 to 100`() {
        val numbers = Counter().count(1, 100)
        assertEquals(IntRange(1, 100), numbers)
    }
}
