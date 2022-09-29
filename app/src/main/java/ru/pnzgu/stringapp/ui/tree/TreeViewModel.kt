package ru.pnzgu.stringapp.ui.tree

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TreeViewModel : ViewModel() {
    val time = MutableLiveData<Double>()
    val timeAverage = MutableLiveData<Double>()
    val comparisonCount = MutableLiveData<Long>()
    val comparisonAverage = MutableLiveData<Double>()
    val foundCount = MutableLiveData<Double>()
}