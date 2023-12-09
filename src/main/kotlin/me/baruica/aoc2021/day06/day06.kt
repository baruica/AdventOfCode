package me.baruica.aoc2021.day06

import java.io.File

val ages = File("inputs/2021/day06_sample.txt").readLines().first().split(",").map { it.toInt() }
val lanternfish: MutableList<Lanternfish> = ages.map { age -> Lanternfish(InternalTimer(age)) }.toMutableList()

data class Lanternfish(val internalTimer: InternalTimer) {

    constructor() : this(InternalTimer())

    fun age() {
        when (internalTimer.days) {
            0 -> {
                internalTimer.reset()
                lanternfish.add(Lanternfish())
            }
            else -> internalTimer.decrease()
        }
    }
}

data class InternalTimer(var days: Int) {

    constructor(): this(reset + 2)

    companion object {
        private const val reset = 6
    }

    fun reset() {
        days = reset
    }

    fun decrease() {
        days -= 1
    }
}

fun main() {
    part1()
}

private fun part1() = println(
    howManyLanternfishAfter(18)
)

private fun howManyLanternfishAfter(days: Int): Int {
    for (day in 1..days) {
        lanternfish.forEach { lanternfish -> lanternfish.age() }
    }
    var day = 1

    return lanternfish.count()
}
