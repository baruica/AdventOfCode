package me.baruica.aoc2021

import java.io.File

val lines = File("inputs/2021/Day04.txt").readLines()
val numbersToDraw = lines.first().split(",").map { it.toInt() }
val boards: MutableList<BingoBoard> =
    lines.asSequence().drop(1).filter { it.isNotBlank() }.chunked(5).map { boardRows ->
        val rows = boardRows.map { row -> row.trim().split("  ", " ").map { it.toInt() } }
        val columns = mutableListOf<List<Int>>()
        for (columnIndex in rows.indices) {
            columns.add(
                listOf(
                    rows[0][columnIndex],
                    rows[1][columnIndex],
                    rows[2][columnIndex],
                    rows[3][columnIndex],
                    rows[4][columnIndex]
                )
            )
        }
        BingoBoard(rows, columns)
    }.toMutableList()

data class BingoBoard(
    val rows: List<List<Int>>,
    val cols: List<List<Int>>
) {
    fun sumOfAllUnmarkedNumbers(drawnNumbers: List<Int>): Int {
        for (rowsOrCols in listOf(rows, cols)) {
            rowsOrCols.forEach { rowOrCol ->
                if (drawnNumbers.containsAll(rowOrCol)) {
                    return rowsOrCols.flatten().minus(drawnNumbers.toSet()).sum()
                }
            }
        }
        return 0
    }
}

fun main() {
    part1()
    part2()
}

private fun part1() = println(firstWinningBoardScore())

private fun part2() = println(lastWinningBoardScore())

private fun firstWinningBoardScore(): Int {
    val drawnNumbers = mutableListOf<Int>()

    numbersToDraw.forEach { drawnNumber ->
        drawnNumbers.add(drawnNumber)
        if (drawnNumbers.size >= 5) {
            boards.forEach { board ->
                val sumOfAllUnmarkedNumbers = board.sumOfAllUnmarkedNumbers(drawnNumbers)
                if (sumOfAllUnmarkedNumbers > 0) {
                    return sumOfAllUnmarkedNumbers * drawnNumber
                }
            }
        }
    }
    return 0
}

private fun lastWinningBoardScore(): Int {
    val drawnNumbers = mutableListOf<Int>()
    val winningBoards = mutableListOf<BingoBoard>()

    numbersToDraw.forEach { drawnNumber ->
        if (winningBoards.size < boards.size) {
            drawnNumbers.add(drawnNumber)
            if (drawnNumbers.size >= 5) {
                boards.forEach { board ->
                    if (!winningBoards.contains(board)) {
                        if (board.sumOfAllUnmarkedNumbers(drawnNumbers) > 0) {
                            winningBoards.add(board)
                        }
                    }
                }
            }
        }
    }

    return winningBoards.last().sumOfAllUnmarkedNumbers(drawnNumbers) * drawnNumbers.last()
}