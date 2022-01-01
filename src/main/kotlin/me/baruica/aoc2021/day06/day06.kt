package me.baruica.aoc2021.day06

import java.io.File

val ages = File("inputs/2021/day06_sample.txt").readLines().first().split(",")
val lanternfish: MutableList<Lanternfish> = ages.map { age -> Lanternfish(InternalTimer(age.toInt())) }.toMutableList()

data class Lanternfish(val internalTimer: InternalTimer) {
    fun age() {
        when (internalTimer.days) {
            0 -> {
                internalTimer.days = 6
                lanternfish.add(Lanternfish(InternalTimer(internalTimer.days + 2)))
            }
            else -> internalTimer.days -= 1
        }
    }
}

data class InternalTimer(var days: Int)

fun main() {
    part1()
}

private fun part1() = println(
    howManyLanternfishAfter(80)
)

private fun howManyLanternfishAfter(days: Int): Int {
    for (day in 1..days) {
        lanternfish.forEach { lanternfish ->
            lanternfish.age()
        }
    }

    return lanternfish.count()
}
