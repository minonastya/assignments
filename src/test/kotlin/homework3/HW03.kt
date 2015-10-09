package homework3

import org.junit.Test
import kotlin.test.assertEquals

public class HW03 {
    var tree = AvlTree(AvlTree(AvlTree(null, 3, null ), 4, AvlTree(null, 5, null)), 6, AvlTree(null, 7, null))
/*
| | _
| 7
| | _
6
| | | _
| | 5
| | | _
| 4
| | | _
| | 3
| | | _
*/

    @Test
    fun testSearch1(){assertEquals(tree.search(3), true)}
    @Test
    fun testSearch2(){assertEquals(tree.search(8), false)}

    var treeAfterInsert2 = AvlTree(AvlTree(AvlTree(null, 2, null), 3, null), 4, AvlTree(AvlTree(null, 5, null), 6, AvlTree(null, 7, null)))
/*
| | | _
| | 7
| | | _
| 6
| | | _
| | 5
| | | _
4
| | _
| 3
| | | _
| | 2
| | | _
 */
    @Test
    fun testInserted1(){assertEquals(tree.insert(2).toText(), treeAfterInsert2.toText())}

    var treeAfterDelete3 = AvlTree(AvlTree(null, 2, null), 4, AvlTree(AvlTree(null, 5, null), 6, AvlTree(null, 7, null)))
    /*
| | | _
| | 7
| | | _
| 6
| | | _
| | 5
| | | _
4
| | _
| 2
| | _
     */

    @Test
    fun testDeleted1(){assertEquals(treeAfterInsert2.delete(3).toText(), treeAfterDelete3.toText())}

    var treeAfterInsert10 = AvlTree(AvlTree(AvlTree(null, 2, null), 4, AvlTree(null, 5, null)), 6, AvlTree(null ,7, AvlTree(null, 10, null)))

    /*
| | | _
| | 10
| | | _
| 7
| | _
6
| | | _
| | 5
| | | _
| 4
| | | _
| | 2
| | | _
     */

    @Test
    fun testInsert2(){assertEquals(treeAfterDelete3.insert(10).toText(), treeAfterInsert10.toText())}

    var treeAfterDelete6 = AvlTree(AvlTree(AvlTree(null, 2, null), 4, AvlTree(null, 5, null)), 7, AvlTree(null, 10, null))

    /*
| | _
| 10
| | _
7
| | | _
| | 5
| | | _
| 4
| | | _
| | 2
| | | _
     */

    @Test
    fun testDelete2(){assertEquals(treeAfterInsert10.delete(6).toText(), treeAfterDelete6.toText())}
}
