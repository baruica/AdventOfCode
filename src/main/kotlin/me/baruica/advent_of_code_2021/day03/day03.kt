package me.baruica.advent_of_code_2021.day03

import java.io.File

val diagnosticReport = File("inputs/day03.txt").readLines()

fun main() {
    println(part1())
}

private fun part1(): Int {
    val counts = counts()
    val gammaRate = counts.fold("") { gRate, count -> if (isMostCommon(count)) gRate+"1" else gRate+"0" }
    val epsilonRate = counts.fold("") { eRate, count -> if (isLeastCommon(count)) eRate+"1" else eRate+"0" }

    return gammaRate.toInt(2) * epsilonRate.toInt(2)
}

private fun counts(): Array<Int> {
    val values = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    diagnosticReport.forEach {
        it.forEachIndexed { i, char -> values[i] += char.digitToInt() }
    }

    return values
}

private fun isMostCommon(count: Int): Boolean = count > (diagnosticReport.size / 2)

private fun isLeastCommon(count: Int): Boolean = count < (diagnosticReport.size / 2)