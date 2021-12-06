package me.baruica.advent_of_code_2021.day02

import java.io.File

val commands = File("inputs/day02.txt").readLines().map {
    val (command, units) = it.split(" ")
    Pair(command, units.toInt())
}

fun main() {
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
