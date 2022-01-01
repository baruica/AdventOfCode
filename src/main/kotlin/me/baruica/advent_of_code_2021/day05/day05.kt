package me.baruica.advent_of_code_2021.day05

import java.io.File

val lineSegments = File("inputs/day05_sample.txt").readLines().map { lineOfVent ->
    val (end1, end2) = lineOfVent.split(" -> ")
    val (end1x, end1y) = end1.split(",").map { it.toInt() }
    val (end2x, end2y) = end2.split(",").map { it.toInt() }
    LineSegment(end1x, end1y, end2x, end2y)
}

data class LineSegment(val end1x: Int, val end1y: Int, val end2x: Int, val end2y: Int) {
    val points: ClosedRange<Point> = when {
        Point(end1x, end1y) < Point(end2x, end2y) -> Point(end1x, end1y)..Point(end2x, end2y)
        else -> Point(end2x, end2y)..Point(end1x, end1y)
    }

    fun isHorizontalOrVertical(): Boolean = (end1x == end2x) || (end1y == end2y)
}

data class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int = when {
        (x > other.x && y == other.y) || (x == other.x && y > other.y) || (x > other.x && y > other.y) -> 1
        (x < other.x && y == other.y) || (x == other.x && y < other.y) || (x < other.x && y < other.y) -> -1
        else -> 0
    }
}

fun main() {
    part1()
}

fun part1() = println(numberOfPointsWhereAtLeastTwoLinesOverlap(lineSegments.filter { it.isHorizontalOrVertical() }))

fun numberOfPointsWhereAtLeastTwoLinesOverlap(lineSegments: List<LineSegment>): Int {
    var overlappingPoints = 0

    for (x in 0 ..9) {
        for (y in 0..9) {
            val point = Point(x, y)
            if (lineSegments.count { lineSegment -> lineSegment.points.contains(point)} >= 2) {
                overlappingPoints++
            }
        }
    }

    return overlappingPoints
}
