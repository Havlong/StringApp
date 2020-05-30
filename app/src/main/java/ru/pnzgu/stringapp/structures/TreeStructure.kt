package ru.pnzgu.stringapp.structures

import ru.pnzgu.stringapp.DataStructure

class TreeStructure(private val onCompare: () -> Unit) : DataStructure {
    private val tree = mutableListOf<MutableList<Pair<Char, Int>>>()

    init {
        tree.clear()
    }

    override fun clear() {
        tree.clear()
        tree.add(mutableListOf())
    }

    override fun add(word: String): Boolean {
        var pos = 0
        var prevPos: Int
        for (i in 0 until word.length - 1) {
            val c = word[i]
            prevPos = pos
            for ((char, nextPos) in tree[pos]) {
                onCompare()
                if (char == c && nextPos != -1) {
                    pos = nextPos
                    break
                }
            }
            onCompare()
            if (pos == prevPos) {
                pos = tree.size
                tree.add(mutableListOf())
                tree[prevPos].add(c to pos)
            }
        }
        onCompare()
        return if (word.last() to -1 !in tree[pos]) {
            tree[pos].add(word.last() to -1)
            true
        } else {
            false
        }
    }

    override fun contains(word: String): Boolean {
        var pos = 0
        var prevPos: Int
        for (i in 0 until word.length - 1) {
            val c = word[i]
            prevPos = pos
            for ((char, nextPos) in tree[pos]) {
                onCompare()
                if (char == c && nextPos != -1) {
                    pos = nextPos
                    break
                }
            }
            onCompare()
            if (pos == prevPos) {
                return false
            }
        }
        onCompare()
        return word.last() to -1 in tree[pos]
    }

    override fun remove(word: String): Boolean {
        var pos = 0
        var prevPos: Int
        for (i in 0 until word.length - 1) {
            val c = word[i]
            prevPos = pos
            for ((char, nextPos) in tree[pos]) {
                onCompare()
                if (char == c && nextPos != -1) {
                    pos = nextPos
                    break
                }
            }
            onCompare()
            if (pos == prevPos) {
                return false
            }
        }
        onCompare()
        return if (tree[pos].contains(word.last() to -1)) {
            tree[pos].remove(word.last() to -1)
            true
        } else {
            false
        }
    }
}