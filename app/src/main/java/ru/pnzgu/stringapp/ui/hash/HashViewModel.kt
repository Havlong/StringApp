package ru.pnzgu.stringapp.ui.hash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HashViewModel : ViewModel() {
    val time = MutableLiveData<Long>()
    val timeAverage = MutableLiveData<Double>()
    val comparisonCount = MutableLiveData<Long>()
    val comparisonAverage = MutableLiveData<Double>()
    val foundCount = MutableLiveData<Long>()
}