package ru.pnzgu.stringapp.ui.hash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HashViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hash Fragment"
    }
    val text: LiveData<String> = _text
}