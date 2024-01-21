package me.baruica.aoc2023

import java.io.File

val gameRecords = File("inputs/2023/Day02.txt").readLines()

fun main() {
    val games: List<Game> = gameRecords.map { Game.fromRecord(it) }
}

data class Game(val id: Int, val subsets: List<Map<String, Int>>) {
    companion object {
        fun fromRecord(record: String): Game {
            val subsets: List<Map<String, Int>> = record
                .substringAfter(": ").split("; ")
                .map { colorCounts ->
                    val map = mutableMapOf<String, Int>()

                    colorCounts.split(", ").map { colorCount ->
                        map[colorCount.substringAfter(" ")] = colorCount.substringBefore(" ").toInt()
                    }

                    map.toMap()
                }

            return Game(
                getIdFromGameRecord(record),
                subsets
            )
        }
    }
}

fun getIdFromGameRecord(record: String): Int = record.substringBefore(": ").substringAfter(" ").toInt()