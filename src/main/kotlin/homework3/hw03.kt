package homework3

public class AvlTree(left_param : AvlTree?, internal val key: Int, right_param: AvlTree?){
    public var lTree: AvlTree? = left_param
        get() = $lTree
        set(newLeft: AvlTree?) {
            $lTree = newLeft
            height_f = calcHeight()
        }

    public var rTree: AvlTree? = right_param
        get() = $rTree
        set(newRight: AvlTree?) {
            $rTree = newRight
            height_f = calcHeight()
        }

    private fun calcHeight(): Int =
            1 + Math.max(lTree?.calcHeight() ?: 0, rTree?.calcHeight() ?: 0)

    private var height_f: Int = calcHeight()
    fun height(): Int = height_f

    internal fun bfactor(): Int = ((rTree?.height() ?: 0) - (lTree?.height() ?: 0))

    internal fun rotateRight(): AvlTree? {
        var tmp = this
        var tree = tmp.lTree
        tmp.lTree = tree?.rTree
        tree?.rTree = tmp
        return tree
    }

    internal fun rotateLeft(): AvlTree? {
        var tmp = this
        var tree = tmp.rTree
        tmp.rTree = tree?.lTree
        tree?.lTree = tmp
        return tree
    }

    internal fun balance(): AvlTree? {
        if (bfactor() == 2) {
            if ((rTree as AvlTree).bfactor() < 0) rTree = rTree?.rotateRight()
            return rotateLeft()
        }
        if (bfactor() == -2) {
            if ((lTree as AvlTree).bfactor() > 0) lTree = lTree?.rotateLeft()
            return rotateRight()
        }
        return this
    }

    public fun insert(numb: Int): AvlTree? {
        when {
            (key < numb) -> rTree = rTree?.insert(numb) ?: AvlTree(null, numb, null)
            (key > numb) -> lTree = lTree?.insert(numb) ?: AvlTree(null, numb, null)
        }
        return balance()
    }

    public fun delete(numb: Int): AvlTree? {
        fun findMin(tree: AvlTree?): AvlTree? {
            if (tree?.lTree != null) return findMin(tree?.lTree)
            else return tree
        }

        fun removeMin(tree: AvlTree?): AvlTree? {
            when {
                tree == null -> {
                }
                tree.lTree == null -> return tree.rTree
                else -> tree.lTree = removeMin(tree.lTree)
            }
            return tree?.balance()
        }
        when {
            (key < numb) -> rTree = rTree?.delete(numb)
            (key > numb) -> lTree = lTree?.delete(numb)
            else -> {
                var l = lTree
                var r = rTree
                if (r == null) return l
                var min = findMin(r)
                min?.rTree = removeMin(r)
                min?.lTree = l
                return min?.balance()
            }
        }
        return this.balance()
    }


    public fun search(numb: Int): Boolean {
        when {
            (key == numb) -> return true
            (key > numb) -> return lTree?.search(numb) ?: false
            else -> return rTree?.search(numb) ?: false
        }
    }
}

public fun AvlTree?.toText() : String {
    fun AvlTree?.toText_() : List<String> {
        when (this) {
            null -> return listOf("_\n")
            AvlTree(null, this.key , null ) -> return listOf("${key}\n")
            else -> {
                val rText = rTree.toText_().map { "| $it"}
                val lText = lTree.toText_().map { "| $it"}
                val vText = listOf("${key}\n")
                return rText + vText + lText
            }
        }
    }
    val builder = StringBuilder()
    val lines = toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}