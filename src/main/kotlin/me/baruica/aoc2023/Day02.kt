package me.baruica.aoc2023

import java.io.File

val gameRecords = File("inputs/2023/Day02.txt").readLines()

fun main() {
    val games: List<Game> = gameRecords.map { Game.fromRecord(it) }
}

data class Game(val id: Int, val maxBlue: Int, val maxGreen: Int, val maxRed: Int) {
    fun isPossible(color: Pair<String, Int>): Boolean {
        return when (color.first) {
            "blue" -> color.second >= maxBlue
            "green" -> color.second >= maxGreen
            "red" -> color.second >= maxRed
            else -> true
        }
    }

    companion object {
        fun fromRecord(record: String): Game {
            val maxColors = mutableMapOf<String, Int>()

            record
                .substringAfter(": ").split("; ").forEach { colorCounts ->
                    colorCounts.split(", ").forEach { colorCount ->
                        val color = colorCount.substringAfter(" ")
                        val count = colorCount.substringBefore(" ").toInt()
                        if (!maxColors.containsKey(color) || maxColors.getOrDefault(color, 0) < count) {
                            maxColors[color] = count
                        }
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