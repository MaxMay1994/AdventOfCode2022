package day5.part1

import java.io.File
import java.io.StringReader

fun main() {
    val (a, b) = File("src/main/resources/day5/supplyStacks.txt").readText().split("\r\n\r\n")
    val stacks = getStack(a)
    val moves = getMoves(b)
    move(stacks,moves)
    printFirst(stacks)
}

fun printFirst(stack: List<ArrayDeque<Char>>) {
    stack.forEach {
        print(it.first())
    }
}

fun move(stacks: List<ArrayDeque<Char>>, moves: List<Move>): List<ArrayDeque<Char>>{
    moves.forEach {
        for (i in 1..it.amount){
            stacks[it.destination -1].addFirst(stacks[it.source -1].first())
            stacks[it.source -1].removeFirst()
        }
    }
    return stacks
}
fun getMoves(protocol: String): List<Move> {
    return protocol.reader().readLines().map {
        Move.create(it)
    }
}

fun getStack(storage: String): List<ArrayDeque<Char>> {
    val stacks = arrayListOf<ArrayDeque<Char>>()
    storage.reader().readColumn().forEach() {
        val stackList = it.replace(" ", "").toCharArray().toList()
        val stack = ArrayDeque(stackList)
        if (!stack.isEmpty() && stack.last().isDigit()) {
            stack.removeLast()
            stacks.add(stack)
        }
    }
    return stacks
}

fun StringReader.readColumn(): ArrayList<String> {
    val list = arrayListOf<String>()
    var index = 0
    readText().map {

        when (it) {
            Char(10), Char(13) -> index = 0
            else -> {
                list.set(index, list.getOrCreate(index, String()).plus(it))
                index += 1
            }
        }
    }
    return list
}

fun <T> ArrayList<T>.getOrCreate(index: Int, instance: T): T {
    return if (index in 0..lastIndex) {
        get(index)
    } else {
        add(index, instance)
        get(index)
    }
}

data class Move(val amount: Int, val source: Int, val destination: Int) {
    companion object {
        val AMOUNT_PREFIX = "move "
        val SOURCE_PREFIX = " from "
        val DESTINATION_PREFIX = " to "
        fun create(line: String): Move {
            return Move(line.substring(line.lastIndexOf(AMOUNT_PREFIX) + AMOUNT_PREFIX.length, line.indexOf(SOURCE_PREFIX)).toInt(),
                line.substring(line.lastIndexOf(SOURCE_PREFIX) + SOURCE_PREFIX.length, line.indexOf(DESTINATION_PREFIX)).toInt(),
                line.substring(line.lastIndexOf(DESTINATION_PREFIX) + DESTINATION_PREFIX.length).toInt())
        }
    }

}


