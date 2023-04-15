package day4.part1

import java.io.File

fun main(){
    val result = countFullyContainedAssignments(File("src/main/resources/day4/campCleanup.txt"))
    println(result)
}

fun countFullyContainedAssignments(input: File): Int{
    return input.readLines().map {
        val (a, b) = it.split(",")
        val (a1, a2) = a.split("-")
        val (b1, b2) = b.split("-")
        IntRange(a1.toInt(), a2.toInt()) to IntRange(b1.toInt(), b2.toInt())
    }.count {
        it.first fullyContains it.second || it.second fullyContains it.first
    }
}

infix fun IntRange.fullyContains(other: IntRange): Boolean{
    return first <= other.first && last >= other.last;
}

