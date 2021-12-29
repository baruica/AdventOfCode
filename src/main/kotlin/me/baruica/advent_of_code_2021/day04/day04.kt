package me.baruica.advent_of_code_2021.day04

import java.io.File

val lines = File("inputs/day04.txt").readLines()
val numbersToDraw = lines.first().split(",").map { it.toInt() }
val boards = lines.drop(1).filter { it.isNotBlank() }.chunked(5).map { boardRows ->
    val rows = boardRows.map { strRow -> strRow.trim().replace("  ", " ").split(" ").map { it.toInt() } }
    val columns = mutableListOf<List<Int>>()
    for (columnIndex in rows.indices) {
        columns.add(listOf(rows[0][columnIndex], rows[1][columnIndex], rows[2][columnIndex], rows[3][columnIndex], rows[4][columnIndex]))
    }
    Board(rows, columns)
}

data class Board(
    val rows: List<List<Int>>,
    val cols: List<List<Int>>
) {
    fun sumOfAllUnmarkedNumbers(drawnNumbers: List<Int>): Int {
        for (rowsOrCols in listOf(rows, cols)) {
            rowsOrCols.forEach { rowOrCol ->
                if (drawnNumbers.containsAll(rowOrCol)) {
                    return rowsOrCols.flatten().minus(drawnNumbers).sum()
                }
            }
        }
        return 0
    }
}

fun main() {
    part1()
}

fun part1() = println(winningBoardScore())

private fun winningBoardScore(): Int {
    val drawnNumbers = mutableListOf<Int>()

    numbersToDraw.forEach { drawnNumber ->
        drawnNumbers.add(drawnNumber)
        if (drawnNumbers.size >= 5) {
            boards.forEach { board ->
                val sumOfAllUnmarkedNumbers = board.sumOfAllUnmarkedNumbers(drawnNumbers)
                if (sumOfAllUnmarkedNumbers > 0) {
                    return sumOfAllUnmarkedNumbers * drawnNumbers.last()
                }
            }
        }
    }
    return 0
}
