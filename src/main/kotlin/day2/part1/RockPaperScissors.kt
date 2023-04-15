package day2.part1

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

fun calculateMatch(opponentHand: Char, yourHand: Char): Int{
    val shapeScore = getShapeScore(yourHand)
    val battleScore = Shape.valueOf(yourHand).battle(Shape.valueOf(opponentHand))
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
                'X', 'A' -> ROCK
                'Y', 'B' -> PAPER
                'Z', 'C' -> SCISSORS
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

}
