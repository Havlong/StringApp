package ru.pnzgu.stringapp.ui.hash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.pnzgu.stringapp.R

class HashFragment : Fragment() {

    private val hashViewModel: HashViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hash, container, false)

        val timeField: TextView = root.findViewById(R.id.timeField)
        val timeAvgField: TextView = root.findViewById(R.id.timeAvgField)
        val cmpField: TextView = root.findViewById(R.id.cmpField)
        val cmpAvgField: TextView = root.findViewById(R.id.cmpAvgField)
        val foundField: TextView = root.findViewById(R.id.foundField)

        timeField.text = "${hashViewModel.time.value} мс"
        timeAvgField.text = "${hashViewModel.timeAverage.value} мс"
        cmpField.text = "${hashViewModel.comparisonCount.value}"
        cmpAvgField.text = "${hashViewModel.comparisonAverage.value}"
        foundField.text = "${hashViewModel.foundCount.value} ключей"

        return root
    }
}
