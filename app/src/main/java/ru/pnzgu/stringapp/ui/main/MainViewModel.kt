package ru.pnzgu.stringapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val n = MutableLiveData<Int>()
    val k = MutableLiveData<Int>()
}
