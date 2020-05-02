package com.willmadison.`fun`

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.Before
import org.junit.Test

class FunTest {

    private lateinit var speaker: Fun

    @Before
    fun setUp() {
        speaker = Fun()
    }

    @Test
    fun `it should speak when given an name`() {
        assertThat(speaker.Say("Ms. Hannigan")).isEqualTo("Hello, Ms. Hannigan")
    }
}