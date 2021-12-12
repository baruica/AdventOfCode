package me.baruica.advent_of_code_2021.day04

import java.io.File

val readLines = File("inputs/day04.txt").readLines()
val numbersToDraw = readLines.first().split(",")
val boards = readLines.drop(1).filter { it.isNotBlank() }.chunked(5).map { Board(it) }

data class Board(val rows: List<String>) {
    fun hasNumber(number: String): Boolean {
        return !rows.find { it.contains(number) }.isNullOrBlank()
    }
}

fun main() {
    part1()
}

fun part1() {
    numbersToDraw.forEach { number ->
        boards.forEach { board ->
            if (board.hasNumber(number)) {
                if (board)
            }
        }
    }
}