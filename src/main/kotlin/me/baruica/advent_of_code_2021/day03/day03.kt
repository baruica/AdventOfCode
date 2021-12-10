package me.baruica.advent_of_code_2021.day03

import java.io.File

val diagnosticReport = File("inputs/day03.txt").readLines()

fun main() {
    part1()
    part2()
}

private fun part1() {
    val counts = counts()
    println(gammaRate(counts).toInt(2) * epsilonRate(counts).toInt(2))
}

private fun counts(): Array<Int> {
    val values = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    diagnosticReport.forEach {
        it.forEachIndexed { i, char -> values[i] += char.digitToInt() }
    }

    return values
}

private fun gammaRate(counts: Array<Int>) = counts.fold("") { gRate, count -> if (count > (diagnosticReport.size / 2)) gRate+"1" else gRate+"0" }

private fun epsilonRate(counts: Array<Int>) = counts.fold("") { eRate, count -> if (count < (diagnosticReport.size / 2)) eRate+"1" else eRate+"0" }

private fun part2() {
    println(oxygenGeneratorRating(diagnosticReport).toInt(2) * co2ScrubberRating(diagnosticReport).toInt(2))
}

private fun oxygenGeneratorRating(numbers: List<String>): String {
    var oxygenGeneratorRating = ""
    var filteredNumbers: List<String>

    for (currentBitPosition in numbers.first().indices) {
        val bitCriteria = if (numbers.sumOf { it[currentBitPosition].digitToInt() } >= (numbers.size / 2)) 1 else 0
        filteredNumbers = numbers.filter { it[currentBitPosition].digitToInt() == bitCriteria }

        if (filteredNumbers.size == 1) {
            oxygenGeneratorRating = filteredNumbers.first()
            break
        }
    }

    return oxygenGeneratorRating
}

private fun co2ScrubberRating(numbers: List<String>): String {
    var co2ScrubberRating = ""
    var filteredNumbers: List<String>

    for (currentBitPosition in numbers.first().indices) {
        val bitCriteria = if (numbers.sumOf { it[currentBitPosition].digitToInt() } <= (numbers.size / 2)) 0 else 1
        filteredNumbers = numbers.filter { it[currentBitPosition].digitToInt() == bitCriteria }

        if (filteredNumbers.size == 1) {
            co2ScrubberRating = filteredNumbers.first()
            break
        }
    }

    return co2ScrubberRating
}