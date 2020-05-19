package ru.pnzgu.stringapp.ui.sorted

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SortedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sorted Fragment"
    }
    val text: LiveData<String> = _text
}