package me.baruica.advent_of_code_2021.day02

import java.io.File

val commands = File("inputs/day02.txt").readLines().map {
    val (command, units) = it.split(" ")
    Pair(command, units.toInt())
}

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int {
    var horizontalPosition = 0
    var depth = 0

    for ((command, units) in commands) {
        when (command) {
            "forward" -> horizontalPosition += units
            "down" -> depth += units
            "up" -> depth -= units
        }
    }

    return horizontalPosition * depth
}

fun part2(): Int {
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

    return horizontalPosition * depth
}
