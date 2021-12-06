package me.baruica.advent_of_code_2021.day01

import java.io.File

val depths = File("inputs/day01.txt").readLines().map { it.toInt() }

fun main() {
    println(
        depths
            .windowed(2)
            .count { (a, b) -> b > a }
    )

    println(
        depths
            .windowed(4)
            .count { (a, b, c, d) -> (b + c + d) > (a + b + c) }
    )
}
