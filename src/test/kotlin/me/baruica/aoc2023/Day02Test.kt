package me.baruica.aoc2023

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

val game1 = Game(1, 6, 2, 4)
val game2 = Game(2, 4, 3, 1)
val game3 = Game(3, 6, 13, 20)
val game4 = Game(4, 15, 3, 14)
val game5 = Game(5, 2, 3, 6)

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

    "filer games only possible with 14 blue cubes" {
        forAll(
            row(game1, true),
            row(game2, true),
            row(game3, true),
            row(game4, false),
            row(game5, true)
        ) { game, possible ->
            game.isPossible("blue", 14) shouldBe possible
        }
    }

    "filer games only possible with 13 green cubes" {
        forAll(
            row(game1, true),
            row(game2, true),
            row(game3, true),
            row(game4, true),
            row(game5, true)
        ) { game, possible ->
            game.isPossible("green", 13) shouldBe possible
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
            game.isPossible("red", 12) shouldBe possible
        }
    }

    "sum of the ids of possible games" {
        listOf(game1, game2, game3, game4, game5)
            .filter { game ->
                game.isPossible("blue", 14)
                    && game.isPossible("green", 13)
                    && game.isPossible("red", 12)
            }.sumOf { it.id } shouldBe 8
    }
})