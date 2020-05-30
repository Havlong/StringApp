package ru.pnzgu.stringapp.ui.sorted

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SortedViewModel : ViewModel() {
    val time = MutableLiveData<Long>()
    val timeAverage = MutableLiveData<Double>()
    val comparisonCount = MutableLiveData<Long>()
    val comparisonAverage = MutableLiveData<Double>()
    val foundCount = MutableLiveData<Long>()
}