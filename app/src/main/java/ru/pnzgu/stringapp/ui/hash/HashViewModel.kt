package ru.pnzgu.stringapp.ui.hash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.pnzgu.stringapp.DataStructure

class HashViewModel : ViewModel(), DataStructure {

    private val hashMap = sortedMapOf<Long, MutableList<String>>()
    private val hashConst = 53
    private val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    val publicList = sortedSetOf<Pair<String, Long>>()

    override fun add(word: String): Boolean {
        val hash = word.polyHash()
        val linkList = hashMap[hash]
        return if (linkList != null) {
            if (word in linkList) {
                false
            } else {
                publicList.add(word to hash)
                linkList.add(word)
                true
            }
        } else {
            publicList.add(word to hash)
            hashMap[hash] = mutableListOf(word)
            true
        }
    }

    override fun contains(word: String): Boolean {
        val hash = word.polyHash()
        val linkList = hashMap[hash]
        return if (linkList != null) {
            word in linkList
        } else {
            false
        }
    }

    override fun remove(word: String): Boolean {
        val hash = word.polyHash()
        val linkList = hashMap[hash]
        return if (linkList != null) {
            return if (word in linkList) {
                publicList.remove(word to hash)
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
        publicList.clear()
    }

    override fun onCleared() {
        clear()
        super.onCleared()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is hash Fragment"
    }
    val text: LiveData<String> = _text
}