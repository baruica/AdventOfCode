package me.baruica.aoc2023

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day02Test : StringSpec({

    "get ID from game" {
        forAll(
            row("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", 1),
            row("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", 2),
            row("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", 3),
            row("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", 4),
            row("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", 5)
        ) { game, id ->
            getIdFromGame(game) shouldBe id
        }
    }

    "get max count of blue cubes from game" {
        forAll(
            row("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", 6),
            row("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", 4),
            row("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", 6),
            row("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", 15),
            row("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", 2)
        ) { game, maxCount ->
            getMaxCountOfCubesOfThisColorFromGame(game, "blue") shouldBe maxCount
        }
    }
})

fun getMaxCountOfCubesOfThisColorFromGame(game: String, color: String): Int {
    return subsets(game).map { subset ->
            subset.split(", ")
                .filter { quantityAndColor -> quantityAndColor.split(" ").last() == color }
                .maxOfOrNull { it.substringBefore(" ").toInt() }
        }
}
