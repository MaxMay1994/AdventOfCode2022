package day2.part2

import java.io.File

fun main(){
    val result = getTotalScore(File("src/main/resources/rockPaperScissor_guide.txt"))
    println(result)
}

fun getTotalScore(input: File): Int{
    return input.readLines().sumOf {
        val (a, b) = it.split(" ")
        calculateMatch(a.first(), b.first())
    }
}

fun calculateMatch(opponentHand: Char, result: Char): Int{
    val opponentShape = Shape.valueOf(opponentHand)
    val yourShape = opponentShape.getNextHand(Result.valueOf(result))
    val shapeScore = yourShape.score
    val battleScore = yourShape.battle(opponentShape)
    return shapeScore + battleScore
}

fun getShapeScore(input: Char): Int {
    return Shape.valueOf(input).score
}

enum class Shape(val score: Int){
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    companion object {
        fun valueOf(value: Char): Shape{
            return when(value){
                'A' -> ROCK
                'B' -> PAPER
                'C' -> SCISSORS
                else -> error("wrong input")
            }
        }
    }

    fun battle(shape: Shape): Int{
        return when (shape) {
            this -> 3
            this.beatenBy() -> 0
            else -> 6
        }
    }

    private fun beatenBy(): Shape{
        return when(this){
            ROCK -> PAPER
            PAPER -> SCISSORS
            SCISSORS -> ROCK
        }
    }

    fun getNextHand(result: Result): Shape{
        return when(result){
            Result.DRAW -> this
            Result.WIN -> this.beatenBy()
            Result.LOSS -> this.winsAgainst()
        }
    }

    private fun winsAgainst(): Shape {
        return when(this){
            ROCK -> SCISSORS
            PAPER -> ROCK
            SCISSORS -> PAPER
        }
    }
}

enum class Result{
    WIN,
    LOSS,
    DRAW;

    companion object {
        fun valueOf(value: Char): Result{
            return when(value){
                'Z' -> WIN
                'X' -> LOSS
                'Y' -> DRAW
                else -> error("wrong input")
            }
        }
    }

}