package me.baruica.aoc2023

import java.io.File

val gameRecords = File("inputs/2023/Day02.txt").readLines()

fun main() {

}

fun getIdFromGame(game: String): Int = game.split(": ").first().split(" ").last().toInt()

fun subsets(game: String): List<String> = game.split(": ").last().split("; ")
