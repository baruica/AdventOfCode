package me.baruica.advent_of_code_2021.day04

import java.io.File

val readLines = File("inputs/day04.txt").readLines()
val numbersToDraw = readLines.first().split(",")
val boards = readLines.drop(1).filter { it.isNotBlank() }.chunked(5).map { println(it[0].split(" ", "  ")); Board.fromList(it) }

data class Board(val rows: List<Int>, val columns: List<Int>) {
    companion object {
        fun fromList(list: List<String>): Board {
            val columns = arrayListOf<List<Int>>()

            //list.forEachIndexed { index, value ->  }

            return Board(list.map { it.toInt() }, list.map { it.toInt() })
        }
    }
}

fun main() {
    part1()
}

fun part1() {
    println(numbersToDraw)
    println(boards)
}
