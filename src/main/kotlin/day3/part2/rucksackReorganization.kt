package day3.part2

import java.io.File

fun main(){
    val result = getBadgePrio(File("src/main/resources/rucksacks.txt"))
    println(result)
}

fun getBadgePrio(input: File): Int{
    //input.readLines().chunked(3)
    return input.readText().split("\r\n", 3).sumOf{
        val (a,b,c) = it.split("\r\n")
        getPrio(getBadge(a,b,c))
    }
}

fun getBadge(a: String, b: String, c: String): Char {
    //a.toSet().intersect(b.toSet())
    val rucksack1 = a.toSet().sorted()
    val rucksack2 = b.toSet().sorted()
    val rucksack3 = c.toSet().sorted()

    var pointer1 = 0
    var pointer2 = 0
    var pointer3 = 0

    while(rucksack1.lastIndex >= pointer1){
        val check = rucksack1[pointer1]
        while(rucksack2.lastIndex >= pointer2 && rucksack2[pointer2] <= check){
            if(check == rucksack2[pointer2]){
                while(rucksack3.lastIndex >= pointer3 && rucksack3[pointer3] <= check){
                    if (check == rucksack3[pointer3]){
                        return check
                    }
                    pointer3++
                }

            }
            pointer2++
            pointer3 = 0
        }
        pointer1++
        pointer2 = 0
    }
    error("Badge not found -> wrong input")
}

fun getPrio(char: Char): Int {
    return when{
        char.isLowerCase() -> char - 'a' + 1
        char.isUpperCase() -> char - 'A' + 27
        else -> error("wrong input")
    }
}

fun String.split(delimiter: String, times: Int): List<String>{
    var currentOffset = 0
    var nextOffset = currentOffset
    val result = ArrayList<String>()
    do{
        for(i in 1..times){
            nextOffset = indexOf(delimiter, nextOffset + delimiter.length)
            when (nextOffset) {
                -1 -> {
                    nextOffset = lastIndex + 1
                    break
                }
            }
        }
        result.add(substring(currentOffset,nextOffset))
        currentOffset = nextOffset + delimiter.length
    }while (nextOffset < lastIndex)

    return result
}



