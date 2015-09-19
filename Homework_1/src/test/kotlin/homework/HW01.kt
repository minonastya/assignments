package homework

import org.junit.Test
import kotlin.test.assertEquals

public class HW01 {

    fun arrToStr(arr:Array<Int>):String{
        var s = ""
        arr.forEach {
            s = s + "$it"
        }
        return s
    }
    @Test
    fun testHeapsort1() {
        var a = arrayOf(1, 2, 3)
        var b = arrayOf(2, 1, 3)
        assertEquals(arrToStr(a), arrToStr(heapsort(b)))
    }
    @Test
    fun testHeapsort2() {
        var c = arrayOf(-2, -1, 0, 1, 2)
        var d = arrayOf(0, 2, -2, -1, 1)
        assertEquals(arrToStr(c), arrToStr(heapsort(d)))
    }
    @Test
    fun testHeapsort3(){
        var e:Array<Int> = emptyArray()
        var f:Array<Int> = emptyArray()
        assertEquals(arrToStr(e),arrToStr(heapsort(f)))
    }
    @Test
    fun testMaxWay1() {
        var a = genTree(0, 5)
        assertEquals(maxWay(0, a), 11)
    }
    @Test
    fun testMaxWay2() {
        var b = Node(Leaf(10), 5, Node (Node(Leaf (4), 0, Leaf(100)), 1, Leaf(2)))
        assertEquals(maxWay(0, b), 106)
    }
    @Test
    fun testMaxWay3(){
        var c = Empty()
        assertEquals(maxWay(0, c), 0)
    }

    @Test
    fun testFold(){
        val a = Node(Leaf(10), 5, Node (Node(Leaf (4), 0, Leaf(100)), 1, Leaf(2)))
        val b = a.foldTopDown({x, y -> x + y}, {x, y -> Math.max(x, y)}, 0)//Max sum in tree
        assertEquals(b, 106)
    }

    var s = 0
    fun peanoToInt(a:Peano):Int {
        when (a) {
            is Zero -> s
            is S -> {
                s++
                peanoToInt(a.p)
            }
            else -> throw Exception("Unknown class")
        }
        return s
    }

    @Test
    fun testPeano1(){
        val a = sum(S(Zero()), S(S(Zero())))
        assertEquals(peanoToInt(a), 3)
    }
    @Test
    fun testPeano2(){
        val a = pow((S(S(Zero()))), sub((S(S(S(S(Zero()))))), Zero()))
        assertEquals(peanoToInt(a), 16)
    }
    @Test
    fun testPeano3(){
        val a = mult(pow(S(S(Zero())), Zero()), S(Zero()))
        assertEquals(peanoToInt(a), 1)
    }

}
