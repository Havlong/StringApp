package ru.pnzgu.stringapp.ui.sorted

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.pnzgu.stringapp.DataStructure

class SortedViewModel : ViewModel(), DataStructure {

    val list = mutableListOf<String>()

    private fun List<String>.lowerBound(key: String): Int {
        var left = 0
        var right = size
        while (left < right) {
            val mid = (left + right) / 2
            if (list[mid] >= key) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    override fun add(word: String): Boolean {
        val pos = list.lowerBound(word)
        return if (pos < list.size && list[pos] == word) {
            false
        } else {
            list.add(pos, word)
            true
        }
    }

    override fun contains(word: String): Boolean {
        val pos = list.lowerBound(word)
        return pos < list.size && list[pos] == word
    }

    override fun remove(word: String): Boolean {
        val pos = list.lowerBound(word)
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

    override fun onCleared() {
        clear()
        super.onCleared()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is sorted Fragment"
    }
    val text: LiveData<String> = _text

}