package ru.pnzgu.stringapp.structures

import ru.pnzgu.stringapp.DataStructure

class HashStructure(private val onCompare: () -> Unit) : DataStructure {

    private val hashMap = sortedMapOf<Long, MutableList<String>>()
    private val hashConst = 53
    private val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun add(word: String): Boolean {
        val hash = word.polyHash()
        val linkList = hashMap[hash]
        onCompare()
        return if (linkList != null) {
            onCompare()
            if (word in linkList) {
                false
            } else {
                linkList.add(word)
                true
            }
        } else {
            hashMap[hash] = mutableListOf(word)
            true
        }
    }

    override fun contains(word: String): Boolean {
        val hash = word.polyHash()
        val linkList = hashMap[hash]
        onCompare()
        return if (linkList != null) {
            onCompare()
            word in linkList
        } else {
            false
        }
    }

    override fun remove(word: String): Boolean {
        val hash = word.polyHash()
        val linkList = hashMap[hash]
        onCompare()
        return if (linkList != null) {
            onCompare()
            return if (word in linkList) {
                linkList.remove(word)
                true
            } else {
                false
            }
        } else {
            false
        }
    }

    private fun String.polyHash(): Long {
        var hash = 0L
        var power = 1L
        for (c in this) {
            hash += (alphabet.indexOf(c) + 1) * power
            power *= hashConst
        }
        return hash
    }

    override fun clear() {
        hashMap.clear()
    }
}