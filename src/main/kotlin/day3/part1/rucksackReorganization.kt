package day3.part1

import java.io.File

fun main() {

    val result = getPrioOfSharedItemTypes(File("src/main/resources/rucksacks.txt"))
    println(result)

}

fun getPrioOfSharedItemTypes(input: File): Int {
    val result = input.readLines().sumOf {
        val (a, b) = it.half()
        calculateSharedItemTypePrio(a, b)
    }
    return result

}

fun getPrio(char: Char): Int {
    return when{
        char.isLowerCase() -> char - 'a' + 1
        char.isUpperCase() -> char - 'A' + 27
        else -> error("wrong input")
    }
}

fun calculateSharedItemTypePrio(a: String, b: String): Int {
    return a.toSet().sumOf {
        when{
            b.contains(it) -> getPrio(it)
            else -> 0
        }
    }
}

fun String.half(): Pair<String, String> {
    this.length
    return this.substring(IntRange(0, this.length / 2 - 1)) to this.substring(this.length / 2)
}