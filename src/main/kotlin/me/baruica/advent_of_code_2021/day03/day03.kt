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
    val oxygenGeneratorRating = findOne(diagnosticReport, ::mostCommon)
    val co2ScrubberRating = findOne(diagnosticReport, ::leastCommon)
    println(oxygenGeneratorRating * co2ScrubberRating)
}

fun findOne(numbers: List<String>, bitCriteriaFn: (List<String>, Int, Int) -> Int): Int {
    var filteredNumbers: List<String> = numbers
    var currentBitPosition = 0

    while (filteredNumbers.size > 1) {
        val bitCriteria = bitCriteriaFn(filteredNumbers, currentBitPosition, filteredNumbers.size)
        filteredNumbers = filteredNumbers.filter { it[currentBitPosition].digitToInt() == bitCriteria }
        currentBitPosition += 1
    }

    return filteredNumbers.first().toInt(2)
}

private fun mostCommon(filteredNumbers: List<String>, currentBitPosition: Int, size: Int): Int {
    val count = filteredNumbers.count { it[currentBitPosition] == '1' }
    return if (count >= (size - count)) 1 else 0
}

private fun leastCommon(filteredNumbers: List<String>, currentBitPosition: Int, size: Int): Int {
    val count = filteredNumbers.count { it[currentBitPosition] == '0' }
    return if (count <= (size - count)) 0 else 1
}
