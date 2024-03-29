package me.baruica.aoc2021

import java.io.File

val commands = File("inputs/2021/Day02.txt").readLines().map {
    val (command, units) = it.split(" ")
    Pair(command, units.toInt())
}

fun main() {
    part1()
    part2()
}

private fun part1() {
    var horizontalPosition = 0
    var depth = 0

    for ((command, units) in commands) {
        when (command) {
            "forward" -> horizontalPosition += units
            "down" -> depth += units
            "up" -> depth -= units
        }
    }

    println(horizontalPosition * depth)
}

private fun part2() {
    var horizontalPosition = 0
    var depth = 0
    var aim = 0

    for ((command, units) in commands) {
        when (command) {
            "forward" -> {
                horizontalPosition += units
                depth += aim * units
            }

            "down" -> aim += units
            "up" -> aim -= units
        }
    }

    println(horizontalPosition * depth)
}