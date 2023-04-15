import java.io.File

fun getBiggestRation(input: File): Int{
    val text = input.readText()
    val elfRationList = text.split("\r\n\r\n").map { ration -> ration.lines().sumOf { it.toInt() } }
    return elfRationList.max()
}

fun getTopThreeBiggestRation(input: File): Int{
    val text = input.readText()
    val elfRationList = text.split("\r\n\r\n").map { ration -> ration.lines().sumOf { it.toInt() } }
    return elfRationList.sortedDescending().take(3).sum()
}