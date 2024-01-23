package me.baruica.aoc2023

import java.io.File

val gameRecords = File("inputs/2023/Day02.txt").readLines()

fun main() {
    part1()
}

private fun part1() {
    val sumOfPossibleIds = gameRecords.map { Game.fromRecord(it) }
        .filter { game -> game.isPossible(14, 13, 12) }
        .sumOf { it.id }

    println(sumOfPossibleIds)
}

data class Game(val id: Int, val maxBlue: Int, val maxGreen: Int, val maxRed: Int) {

    fun isPossible(blue: Int, green: Int, red: Int): Boolean =
        blue >= maxBlue && green >= maxGreen && red >= maxRed

    companion object {
        fun fromRecord(record: String): Game {
            val maxColors = mutableMapOf<String, Int>()

            record
                .substringAfter(": ").split("; ").forEach { colorCounts ->
                    colorCounts.split(", ").forEach { colorCount ->
                        val color = colorCount.substringAfter(" ")
                        val count = colorCount.substringBefore(" ").toInt()

                        maxColors[color] = maxOf(count, maxColors[color] ?: count)
                    }
                }

            return Game(
                record.substringBefore(": ").substringAfter(" ").toInt(),
                maxColors.getOrDefault("blue", 0),
                maxColors.getOrDefault("green", 0),
                maxColors.getOrDefault("red", 0)
            )
        }
    }
}