package ru.pnzgu.stringapp.structures

import DigitalTree
import ru.pnzgu.stringapp.DataStructure

class TreeStructure(private val onCompare: () -> Unit) : DataStructure {
    private val tree = DigitalTree()

    override fun clear() {
        tree.clear()
    }

    override fun add(word: String): Boolean {
        if (word in tree) return false
        tree += word
        return true
    }

    override fun contains(word: String): Boolean = word in tree

    override fun remove(word: String): Boolean {
        if (word !in tree) return false
        tree -= word
        return true
    }
}