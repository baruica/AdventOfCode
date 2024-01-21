package me.baruica.aoc2023

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

val game1 = Game(1, listOf(mapOf("blue" to 3, "red" to 4), mapOf("red" to 1, "green" to 2, "blue" to 6), mapOf("green" to 2)))
val game2 = Game(2, listOf(mapOf("blue" to 1, "green" to 2), mapOf("green" to 3, "blue" to 4, "red" to 1), mapOf("green" to 1, "blue" to 1)))
val game3 = Game(3, listOf(mapOf("green" to 8, "blue" to 6, "red" to 20), mapOf("blue" to 5, "red" to 4, "green" to 13), mapOf("green" to 5, "red" to 1)))
val game4 = Game(4, listOf(mapOf("green" to 1, "red" to 3, "blue" to 6), mapOf("green" to 3, "red" to 6), mapOf("green" to 3, "blue" to 15, "red" to 14)))
val game5 = Game(5, listOf(mapOf("red" to 6, "blue" to 1, "green" to 3), mapOf("blue" to 2, "red" to 1, "green" to 2)))

class Day02Test : StringSpec({

    "get Game from game record" {
        forAll(
            row("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", game1),
            row("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", game2),
            row("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", game3),
            row("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", game4),
            row("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", game5)
        ) { gameRecord, game ->
            Game.fromRecord(gameRecord) shouldBe game
        }
    }

    "filer games only possible with 12 red cubes" {
        forAll(
            row(game1, true),
            row(game2, true),
            row(game3, false),
            row(game4, false),
            row(game5, true)
        ) { game, possible ->
            game.isPossible("red" to 12) shouldBe possible
        }
    }
})