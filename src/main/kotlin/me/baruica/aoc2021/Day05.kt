package me.baruica.aoc2021

import java.io.File

val lineSegments = File("inputs/2021/day05.txt").readLines().map { lineOfVent ->
    val (end1, end2) = lineOfVent.split(" -> ")
    val (end1x, end1y) = end1.split(",").map { it.toInt() }
    val (end2x, end2y) = end2.split(",").map { it.toInt() }
    LineSegment(end1x, end1y, end2x, end2y)
}

data class LineSegment(val end1x: Int, val end1y: Int, val end2x: Int, val end2y: Int) {
    private var points: MutableSet<Point> = mutableSetOf()

    fun isHorizontalOrVertical(): Boolean = (end1x == end2x) || (end1y == end2y)

    fun points(): Set<Point> {
        if (points.isEmpty()) {
            val xRange = if (end1x < end2x) end1x..end2x else end1x downTo end2x
            val yRange = if (end1y < end2y) end1y..end2y else end1y downTo end2y

            if (isHorizontalOrVertical()) {
                for (x in xRange) {
                    for (y in yRange) {
                        points.add(Point(x, y))
                    }
                }
            } else {
                for ((i, x) in xRange.withIndex()) {
                    points.add(Point(x, yRange.elementAt(i)))
                }
            }
        }
        return points
    }
}

data class Point(val x: Int, val y: Int)

fun main() {
    part1()
    part2()
}

private fun part1() = println(
    numberOfPointsWhereAtLeastTwoLinesOverlap(
        lineSegments.filter { it.isHorizontalOrVertical() }
    )
)

private fun part2() = println(
    numberOfPointsWhereAtLeastTwoLinesOverlap(lineSegments)
)

private fun numberOfPointsWhereAtLeastTwoLinesOverlap(lineSegments: List<LineSegment>): Int {
    var overlappingPoints = 0

    for (x in 0..999) {
        for (y in 0..999) {
            val point = Point(x, y)
            if (lineSegments.count { lineSegment -> lineSegment.points().contains(point) } >= 2) {
                overlappingPoints++
            }
        }
    }

    return overlappingPoints
}