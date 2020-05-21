package ru.pnzgu.stringapp.ui.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.pnzgu.stringapp.DataStructure

class TreeViewModel : ViewModel(), DataStructure {

    private val tree = mutableListOf<MutableList<Pair<Char, Int>>>()
    var publicList = sortedSetOf<String>()

    init {
        tree.clear()
    }

    override fun clear() {
        tree.clear()
        publicList.clear()
        tree.add(mutableListOf())
    }

    override fun onCleared() {
        clear()
        super.onCleared()
    }

    override fun add(word: String): Boolean {
        publicList.add(word)
        var pos = 0
        var prevPos: Int
        for (i in 0 until word.length - 1) {
            val c = word[i]
            prevPos = pos
            for ((char, nextPos) in tree[pos]) {
                if (char == c && nextPos != -1) {
                    pos = nextPos
                    break
                }
            }
            if (pos == prevPos) {
                pos = tree.size
                tree.add(mutableListOf())
                tree[prevPos].add(c to pos)
            }
        }
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
                if (char == c && nextPos != -1) {
                    pos = nextPos
                    break
                }
            }
            if (pos == prevPos) {
                return false
            }
        }
        return word.last() to -1 in tree[pos]
    }

    override fun remove(word: String): Boolean {
        var pos = 0
        var prevPos: Int
        for (i in 0 until word.length - 1) {
            val c = word[i]
            prevPos = pos
            for ((char, nextPos) in tree[pos]) {
                if (char == c && nextPos != -1) {
                    pos = nextPos
                    break
                }
            }
            if (pos == prevPos) {
                return false
            }
        }
        return if (tree[pos].contains(word.last() to -1)) {
            publicList.remove(word)
            tree[pos].remove(word.last() to -1)
            true
        } else {
            false
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is tree Fragment"
    }
    val text: LiveData<String> = _text
}