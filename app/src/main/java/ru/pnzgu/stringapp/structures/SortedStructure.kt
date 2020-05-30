package ru.pnzgu.stringapp.structures

import ru.pnzgu.stringapp.DataStructure

class SortedStructure(private val onCompare: () -> Unit) : DataStructure {
    private val list = mutableListOf<String>()

    private fun List<String>.lowerBound(key: String): Int {
        var left = 0
        var right = size
        while (left < right) {
            onCompare()
            val mid = (left + right) / 2
            onCompare()
            if (list[mid] >= key) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        onCompare()
        return left
    }

    override fun add(word: String): Boolean {
        val pos = list.lowerBound(word)
        onCompare()
        return if (pos < list.size && list[pos] == word) {
            false
        } else {
            list.add(pos, word)
            true
        }
    }

    override fun contains(word: String): Boolean {
        val pos = list.lowerBound(word)
        onCompare()
        return pos < list.size && list[pos] == word
    }

    override fun remove(word: String): Boolean {
        val pos = list.lowerBound(word)
        onCompare()
        return if (pos < list.size && list[pos] == word) {
            list.removeAt(pos)
            true
        } else {
            false
        }
    }

    override fun clear() {
        list.clear()
    }
}