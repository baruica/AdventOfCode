package me.baruica.aoc2021

import java.io.File

val ages = File("inputs/2021/Day06_sample.txt").readLines().first().split(",").map { it.toInt() }
val lanternFish: MutableList<LanternFish> = ages.map { age -> LanternFish(InternalTimer(age)) }.toMutableList()

data class LanternFish(val internalTimer: InternalTimer) {

    constructor() : this(InternalTimer())

    fun age() {
        when (internalTimer.days) {
            0 -> {
                internalTimer.reset()
                lanternFish.add(LanternFish())
            }

            else -> internalTimer.decrease()
        }
    }
}

data class InternalTimer(var days: Int) {

    constructor() : this(RESET + 2)

    companion object {
        private const val RESET = 6
    }

    fun reset() {
        days = RESET
    }

    fun decrease() {
        days -= 1
    }
}

fun main() {
    part1()
}

private fun part1() = println(
    howManyLanternFishAfter(18)
)

private fun howManyLanternFishAfter(days: Int): Int {
    for (day in 1..days) {
        lanternFish.forEach { lanternFish -> lanternFish.age() }
    }
    var day = 1

    return lanternFish.count()
}