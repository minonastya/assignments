package homework

//Task 1. Heapsort.
fun swap(array:Array<Int>, i:Int, j:Int){
    val tmp = array[i]
    array[i] = array[j]
    array[j] = tmp
}
//Search maximum child
fun child(arr:Array<Int>, par:Int, n:Int):Int {
    if (n - 1 >= par * 2 + 2) {
        if (arr[2 * par + 1] > arr[2 * par + 2]) return (2 * par + 1)
        else return (2 * par + 2)
    }
    else if (n - 1 == par * 2 + 1) return (2 * par + 1)
    else return par
}
//Build binary heap
fun buildTree(arr:Array<Int>):Array<Int>{
    val n = arr.size()
    var i = arr.size()/2 -1
    while (i >= 0){
        val tmp = i
        var ch = child(arr, i, n)
        while (arr[i] < arr[ch]){
            swap(arr, i, ch)
            i = ch
            ch = child(arr, i, n)
        }
        i = tmp - 1
    }
    return arr
}

fun heapsort(arr:Array<Int>):Array<Int>{
    buildTree(arr)
    var n = arr.size() - 1
    while (n > 0){
        swap(arr, 0, n)
        var i = 0
        var ch = child(arr, i, n )
        while (arr[i] < arr[ch]){
            swap(arr, i, ch)
            i = ch
            ch = child(arr, i, n)
        }
        n--
    }
    return arr
}
//Task 2. Search maximum sum in tree.
abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val l : Tree, val value : Int, val r : Tree) : Tree() {}

fun genTree(l:Int, r:Int):Tree{
    if (l > r) return Empty()
    else if (l == r) return Leaf(l)
    else return Node(genTree(l, l + (r - l)/2 - 1),l + (r - l)/2, genTree(l + (r - l)/2 + 1, r ))
}

fun Tree.toText() : String {
    fun Tree.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  -> {
                val lText = l.toText_().map { "| $it"}
                val rText = r.toText_().map { "| $it"}
                val vText = listOf("$value\n")
                return lText + vText + rText
            }
            else -> throw Exception("Unknown class")
        }
    }
    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun maxWay(acc:Int, tree:Tree):Int{
    when (tree){
        is Empty -> return acc
        is Leaf -> return (acc + tree.value)
        is Node -> return Math.max(maxWay(acc+tree.value, tree.r), maxWay(acc + tree.value, tree.l))
        else -> throw Exception("Unknown class")
    }
}

//Task 3. A fold implementation.
fun Tree.foldTopDown(f:(Int, Int) -> Int, fNode:(Int, Int) -> Int, acc:Int):Int{
    when (this){
        is Empty -> return acc
        is Leaf  -> return f(acc, value)
        is Node  -> {val lRes = l.foldTopDown(f, fNode, f(acc, value))
                     val rRes = r.foldTopDown(f, fNode, f(acc, value))
                     return fNode(lRes, rRes)}
        else     ->  throw Exception("Unknown class")
    }
}

//Task 4. Numbers Peano.
abstract class Peano{}
open class Zero(): Peano(){}
open class S(val p:Peano): Peano(){}

fun printPeano(a:Peano){
    when (a){
        is Zero -> print("Zero")
        is S -> {
            print("S(")
            printPeano(a.p)
            print(")")
        }
    }
}

fun sum(a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return b
        is S -> return(S(sum(a.p, b)))
        else -> throw Exception("Unknown class")
    }
}

fun sub(a:Peano, b:Peano):Peano{
    when (b){
        is Zero -> return a
        is S -> {
            when (a){
                is Zero -> return Zero()
                is S -> return(sub(a.p, b.p))
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}

fun mult(a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return Zero()
        is S -> return sum(mult(a.p, b),b)
        else -> throw Exception("Unknown class")
    }
}

fun pow(a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return Zero()
        is S -> {
            when (b){
                is Zero -> return S(Zero())
                is S -> return mult(pow(a, b.p), a)
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}

fun main(args: Array<String>) {

}