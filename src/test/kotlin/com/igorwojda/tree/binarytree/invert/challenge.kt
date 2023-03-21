package com.igorwojda.tree.binarytree.invert

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun invert(node: Node<Int>? = null): Node<Int>? {
    if (node == null) {
        return null
    }
    invertNode(node.left, node.right)
    return node
}

private fun invertNode(leftNode: Node<Int>?, rightNode: Node<Int>?) {

    if(leftNode != null && rightNode != null) {
        val rightNodeData = rightNode!!.data
        rightNode.data = leftNode!!.data
        leftNode.data = rightNodeData
        invertNode(leftNode.left, rightNode.right)
        invertNode(leftNode.right, rightNode.left)
    }

}

private class Test {

    @Test
    fun `Invert valid 1-level SBT`() {
        // -- -------Tree------------
        //
        //            1
        // --------------------------

        val node = Node(data = 1)
        val invertTree = invert(node)
        invertTree!!.data shouldBeEqualTo 1
    }

    @Test
    fun `Invert valid 3-level SBT`() {
        // -- -------Tree------------
        //
        //            1
        //          /   \
        //         2      3
        //        / \    / \
        //       4   5  6   7
        //
        // --------------------------

        // -- -------Invert------------
        //
        //            1
        //          /   \
        //         3      2
        //        / \    / \
        //       7   6  5   4
        //
        // --------------------------

        val node = Node(data = 1)
        node.insertLeft(2)
        node.insertRight(3)
        node.left!!.insertLeft(4)
        node.left!!.insertRight(5)
        node.right!!.insertLeft(6)
        node.right!!.insertRight(7)
        val invertedTree = invert(node)
        invertedTree!!.left!!.data shouldBeEqualTo 3
        invertedTree!!.right!!.data shouldBeEqualTo 2

        node.left!!.left!!.data shouldBeEqualTo 7
        node.left!!.right!!.data shouldBeEqualTo 6
        node.right!!.left!!.data shouldBeEqualTo 5
        node.right!!.right!!.data shouldBeEqualTo 4 

    }


}

data class Node<T>(
    var left: Node<T>? = null,
    var right: Node<T>? = null,
    var data: T
) {
    fun insertLeft(value: T) {
        this.left = Node(data = value)
    }

    fun insertRight(value: T) {
        this.right = Node(data = value)
    }
}