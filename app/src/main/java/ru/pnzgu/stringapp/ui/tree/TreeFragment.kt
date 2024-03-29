package ru.pnzgu.stringapp.ui.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.pnzgu.stringapp.R

class TreeFragment : Fragment() {

    private val treeViewModel: TreeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tree, container, false)
        val timeField: TextView = root.findViewById(R.id.timeField)
        val timeAvgField: TextView = root.findViewById(R.id.timeAvgField)
        val cmpField: TextView = root.findViewById(R.id.cmpField)
        val cmpAvgField: TextView = root.findViewById(R.id.cmpAvgField)
        val foundField: TextView = root.findViewById(R.id.foundField)

        timeField.text = getString(R.string.time_placeholder, treeViewModel.time.value)
        timeAvgField.text = getString(R.string.time_placeholder, treeViewModel.timeAverage.value)
        cmpField.text = "${treeViewModel.comparisonCount.value}"
        cmpAvgField.text = "${treeViewModel.comparisonAverage.value}"
        foundField.text = getString(R.string.keys_placeholder, treeViewModel.foundCount.value)

        return root
    }
}
