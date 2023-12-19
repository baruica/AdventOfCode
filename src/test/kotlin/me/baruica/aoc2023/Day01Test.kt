package me.baruica.aoc2023

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day01Test : StringSpec({

    "firstValidDigitFound should find the first digit or digit spelled out in letters" {
        forAll(
            row("two1nine", 2),
            row("eightwothree", 8),
            row("abcone2threexyz", 1),
            row("xtwone3four", 2),
            row("4nineeightseven2", 4),
            row("zoneight234", 1),
            row("7pqrstsixteen", 7)
        ) { line, digit ->
            firstValidDigitFound(line) shouldBe digit
        }
    }

    "lastValidDigitFound should find the last digit or digit spelled out in letters" {
        forAll(
            row("two1nine", 9),
            row("eightwothree", 3),
            row("abcone2threexyz", 3),
            row("xtwone3four", 4),
            row("4nineeightseven2", 2),
            row("zoneight234", 4),
            row("7pqrstsixteen", 6)
        ) { line, digit ->
            lastValidDigitFound(line) shouldBe digit
        }
    }
})
