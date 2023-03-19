package com.igorwojda.tree.symetrictree

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun isValidSymetricBinaryTree(node: Node<Int>? = null): Boolean {
    if (node == null) {
        return true
    }
    return isSymetric(node.left, node.right)
}

private fun isSymetric(leftNode: Node<Int>?, rightNode: Node<Int>?): Boolean {
    return if (leftNode == null && rightNode == null)
        true
    else if (leftNode == null || rightNode == null || leftNode.data != rightNode.data)
        false
    else {
         isSymetric(leftNode.left, rightNode.right)
                    && isSymetric(leftNode.right, rightNode.left)
    }
}

private class Test {

    @Test
    fun `Validate valid 1-level SBT`() {
        // -- -------Tree------------
        //
        //            1
        // --------------------------

        val node = Node(data = 1)
        isValidSymetricBinaryTree(node) shouldBeEqualTo true
    }

    @Test
    fun `Validate valid 3-level SBT`() {
        // -- -------Tree------------
        //
        //            1
        //          /   \
        //         2      2
        //        / \    / \
        //       3   4  4   3
        //
        // --------------------------

        val node = Node(data = 1)
        node.insertLeft(2)
        node.insertRight(2)
        node.left!!.insertLeft(3)
        node.left!!.insertRight(4)
        node.right!!.insertLeft(4)
        node.right!!.insertRight(3)
        isValidSymetricBinaryTree(node) shouldBeEqualTo true
    }

    @Test
    fun `Validate invalid 3-level SBT`() {
        // -- -------Tree------------
        //
        //            1
        //          /   \
        //         2      2
        //        / \    / \
        //       3   4  7   3
        //
        // --------------------------

        val node = Node(data = 1)
        node.insertLeft(2)
        node.insertRight(2)
        node.left!!.insertLeft(3)
        node.left!!.insertRight(4)
        node.right!!.insertLeft(7)
        node.right!!.insertRight(3)
        isValidSymetricBinaryTree(node) shouldBeEqualTo false
    }

    @Test
    fun `Validate valid 4-level SBT`() {
        // -- -------Tree------------
        //
        //             1
        //          /      \
        //         2        2
        //        / \      / \
        //       3   4    4   3
        //      / \ / \  / \ / \
        //     5   67  88   76 5
        // --------------------------

        val node = Node(data = 1)
        node.insertLeft(2)
        node.insertRight(2)
        node.left!!.insertLeft(3)
        node.left!!.insertRight(4)
        node.right!!.insertLeft(4)
        node.right!!.insertRight(3)

        node.left!!.left!!.insertLeft(5)
        node.left!!.left!!.insertRight(6)
        node.left!!.right!!.insertLeft(7)
        node.left!!.right!!.insertRight(8)

        node.right!!.left!!.insertLeft(8)
        node.right!!.left!!.insertRight(7)
        node.right!!.right!!.insertLeft(6)
        node.right!!.right!!.insertRight(5)

        isValidSymetricBinaryTree(node) shouldBeEqualTo true
    }

    @Test
    fun `Validate invalid 4-level SBT`() {
        // -- -------Tree------------
        //
        //             1
        //          /      \
        //         2        2
        //        / \      / \
        //       3   4    4   3
        //      / \ / \  / \ / \
        //     5   67  85   76 5
        // --------------------------

        val node = Node(data = 1)
        node.insertLeft(2)
        node.insertRight(2)
        node.left!!.insertLeft(3)
        node.left!!.insertRight(4)
        node.right!!.insertLeft(4)
        node.right!!.insertRight(3)

        node.left!!.left!!.insertLeft(5)
        node.left!!.left!!.insertRight(6)
        node.left!!.right!!.insertLeft(7)
        node.left!!.right!!.insertRight(8)

        node.right!!.left!!.insertLeft(5)
        node.right!!.left!!.insertRight(7)
        node.right!!.right!!.insertLeft(6)
        node.right!!.right!!.insertRight(5)

        isValidSymetricBinaryTree(node) shouldBeEqualTo false
    }
}

data class Node<T>(
    var left: Node<T>? = null,
    var right: Node<T>? = null,
    val data: T
) {
    fun insertLeft(value: T) {
        this.left = Node(data = value)
    }

    fun insertRight(value: T) {
        this.right = Node(data = value)
    }
}