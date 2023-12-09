package me.baruica.aoc2023.day01

import java.io.File

val linesOfText = File("inputs/2023/day01.txt").readLines()

fun main() {
    part1()
    part2()
}

fun part1() = println(
    linesOfText.sumOf { "${firstDigit(it)}${lastDigit(it)}".toInt() }
)

fun part2() = println(
    linesOfText.sumOf { "${firstValidDigitFound(it)}${lastValidDigitFound(it)}".toInt() }
)

fun firstDigit(line: String): String = line.first { it.isDigit() }.toString()
fun lastDigit(line: String): String = line.last { it.isDigit() }.toString()

val digitsSpelledOutWithLetters = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)
fun firstValidDigitFound(line: String): Int {
    val firstDigit: Pair<Int, Int> = firstValidDigit(line)
    val firstDigitSpelledOutWithLetters: Pair<Int, Int> = firstDigitSpelledOutWithLetters(line)

    return if (firstDigit.first < firstDigitSpelledOutWithLetters.first) {
        firstDigit.second
    } else {
        firstDigitSpelledOutWithLetters.second
    }
}
fun firstValidDigit(line: String): Pair<Int, Int> {
    var firstDigit: Pair<Int, Int> = Pair(42, 42)
    for (digit in 1..9) {
        val indexOfFirstDigit = line.indexOf(digit.toString())
        if (indexOfFirstDigit > -1 && indexOfFirstDigit < firstDigit.first)
            firstDigit = Pair(indexOfFirstDigit, digit)
    }
    return firstDigit
}
fun firstDigitSpelledOutWithLetters(line: String): Pair<Int, Int> {
    var firstDigitSpelledOutWithLetters: Pair<Int, Int> = Pair(42, 42)
    for (digitSpelledOutWithLetters in digitsSpelledOutWithLetters.keys) {
        val indexOfFirstDigit = line.indexOf(digitSpelledOutWithLetters)
        if (indexOfFirstDigit > -1 && indexOfFirstDigit < firstDigitSpelledOutWithLetters.first)
            firstDigitSpelledOutWithLetters = Pair(indexOfFirstDigit, digitsSpelledOutWithLetters.getOrDefault(digitSpelledOutWithLetters, 42))
    }
    return firstDigitSpelledOutWithLetters
}

fun lastValidDigitFound(line: String): Int {
    val lastDigit: Pair<Int, Int> = lastValidDigit(line)
    val lastDigitSpelledOutWithLetters: Pair<Int, Int> = lastDigitSpelledOutWithLetters(line)

    return if (lastDigit.first >= lastDigitSpelledOutWithLetters.first) {
        lastDigit.second
    } else {
        lastDigitSpelledOutWithLetters.second
    }
}
fun lastValidDigit(line: String): Pair<Int, Int> {
    var lastDigit: Pair<Int, Int> = Pair(-42, -42)
    for (digit in 1..9) {
        val indexOfLastDigit = line.lastIndexOf(digit.toString())
        if (indexOfLastDigit > -1 && indexOfLastDigit > lastDigit.first)
            lastDigit = Pair(indexOfLastDigit, digit)
    }
    return lastDigit
}
fun lastDigitSpelledOutWithLetters(line: String): Pair<Int, Int> {
    var lastDigitSpelledOutWithLetters: Pair<Int, Int> = Pair(-42, -42)
    for (digitSpelledOutWithLetters in digitsSpelledOutWithLetters.keys) {
        val indexOfLastDigit = line.lastIndexOf(digitSpelledOutWithLetters)
        if (indexOfLastDigit > -1 && indexOfLastDigit > lastDigitSpelledOutWithLetters.first)
            lastDigitSpelledOutWithLetters = Pair(indexOfLastDigit, digitsSpelledOutWithLetters.getOrDefault(digitSpelledOutWithLetters, -42))
    }
    return lastDigitSpelledOutWithLetters
}
