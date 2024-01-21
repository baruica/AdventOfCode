package me.baruica.aoc2023

import java.io.File

val gameRecords = File("inputs/2023/Day02.txt").readLines()

fun main() {
    val games: List<Game> = gameRecords.map { Game.fromRecord(it) }
}

data class Game(val id: Int, val subsets: List<Map<String, Int>>) {
    fun isPossible(color: Pair<String, Int>): Boolean {
        return subsets.
    }

    companion object {
        fun fromRecord(record: String): Game {
            val subsets: List<Map<String, Int>> = record
                .substringAfter(": ").split("; ")
                .map { colorCounts ->
                    val colorCountMap = mutableMapOf<String, Int>()

                    colorCounts.split(", ").map { colorCount ->
                        colorCountMap[colorCount.substringAfter(" ")] = colorCount.substringBefore(" ").toInt()
                    }

                    colorCountMap.toMap()
                }

            return Game(
                record.substringBefore(": ").substringAfter(" ").toInt(),
                subsets
            )
        }
    }
}