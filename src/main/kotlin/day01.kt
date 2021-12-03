package day01

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
            .windowed(3)
            .sumOf { (a, b, c) -> a + b + c }
    )
}
