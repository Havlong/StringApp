package ru.pnzgu.stringapp.ui.sorted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.pnzgu.stringapp.R

class SortedFragment : Fragment() {

    private val sortedViewModel: SortedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sorted, container, false)
        val timeField: TextView = root.findViewById(R.id.timeField)
        val timeAvgField: TextView = root.findViewById(R.id.timeAvgField)
        val cmpField: TextView = root.findViewById(R.id.cmpField)
        val cmpAvgField: TextView = root.findViewById(R.id.cmpAvgField)
        val foundField: TextView = root.findViewById(R.id.foundField)

        timeField.text = "${sortedViewModel.time.value} мс"
        timeAvgField.text = "${sortedViewModel.timeAverage.value} мс"
        cmpField.text = "${sortedViewModel.comparisonCount.value}"
        cmpAvgField.text = "${sortedViewModel.comparisonAverage.value}"
        foundField.text = "${sortedViewModel.foundCount.value} ключей"
        return root
    }
}
