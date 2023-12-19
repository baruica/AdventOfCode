package me.baruica.aoc2021

import java.io.File

val depths = File("inputs/2021/day01.txt").readLines().map { it.toInt() }

private fun main() {
    part1()
    part2()
}

private fun part1() = println(
    depths
        .windowed(2)
        .count { (a, b) -> b > a }
)

private fun part2() = println(
    depths
        .windowed(4)
        .count { (a, b, c, d) -> (b + c + d) > (a + b + c) }
)
