package ru.pnzgu.stringapp.ui.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TreeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tree Fragment"
    }
    val text: LiveData<String> = _text
}