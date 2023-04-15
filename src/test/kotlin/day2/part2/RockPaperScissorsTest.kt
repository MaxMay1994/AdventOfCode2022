package day2.part2

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class RockPaperScissorsTest {

    @Test
    fun getTotalScoreTest(){
        val input = File("src/main/resources/guideTest.txt")
        val result = getTotalScore(input)
        assertEquals(12, result)
    }
}