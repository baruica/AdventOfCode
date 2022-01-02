package me.baruica.aoc2021.day06

import java.io.File

val ages = File("inputs/2021/day06_sample.txt").readLines().first().split(",").map { it.toInt() }
val lanternfish: List<Lanternfish> = ages.map { age -> Lanternfish(InternalTimer(age)) }

data class Lanternfish(val internalTimer: InternalTimer) {
    fun age() {
        when (internalTimer.days) {
            0 -> {
                internalTimer.reset()
                lanternfish.add(Lanternfish(InternalTimer(internalTimer.days + 2)))
            }
            else -> internalTimer.decrease()
        }
    }
}

data class InternalTimer(var days: Int) {
    fun reset() {
        days = 6
    }

    fun decrease() {
        days -= 1
    }
}

fun main() {
    part1()
}

private fun part1() = println(
    howManyLanternfishAfter(80)
)

private fun howManyLanternfishAfter(days: Int): Int {
    for (day in 1..days) {
        lanternfish.map { lanternfish -> lanternfish.age() }
    }

    return lanternfish.count()
}
