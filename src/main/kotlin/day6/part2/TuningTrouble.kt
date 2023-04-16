package day6.part2

import java.io.File

fun main(){
    val input = File("src/main/resources/day6/datastream.txt").readText()
    val startOfMessage = startOfMessageSubroutine(input)
    println(startOfMessage)
}

fun startOfMessageSubroutine(input: String): Int{
    val windowSize = 14
    val window = ArrayDeque<Char>(windowSize)
    var index = 1

    input.toCharArray().forEach {
        window.addToWindow(it,windowSize)
        if(window.size == windowSize){
            if(addCharactersDiffer(window)){
                return index
            }
        }
        index +=1
    }
    return -1
}

fun addCharactersDiffer(list: ArrayDeque<Char>): Boolean {
    val listSize = list.size
    val distinctListSize = list.distinct().size
    return listSize == distinctListSize
}

private fun <E> ArrayDeque<E>.addToWindow(e: E, windowSize: Int) {
    addLast(e)
    while(size > windowSize){
        removeFirst()
    }
}
