package day1

import getBiggestRation
import getTopThreeBiggestRation
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class CalorieCountingTest {

    @Test
    fun getBiggestRationTest(){
        val input = File("src/main/resources/supplies.txt")
        val result = getBiggestRation(input)
        assertEquals(24000,result);
    }

    @Test
    fun getTopThreeBiggestRationTest(){
        val input = File("src/main/resources/supplies.txt")
        val result = getTopThreeBiggestRation(input)
        assertEquals(45000,result);
    }
}