package ru.pnzgu.stringapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.pnzgu.stringapp.DataStructure
import ru.pnzgu.stringapp.R
import ru.pnzgu.stringapp.structures.HashStructure
import ru.pnzgu.stringapp.structures.SortedStructure
import ru.pnzgu.stringapp.structures.TreeStructure
import ru.pnzgu.stringapp.ui.hash.HashViewModel
import ru.pnzgu.stringapp.ui.sorted.SortedViewModel
import ru.pnzgu.stringapp.ui.tree.TreeViewModel
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private val hashViewModel: HashViewModel by activityViewModels()
    private val sortedViewModel: SortedViewModel by activityViewModels()
    private val treeViewModel: TreeViewModel by activityViewModels()

    private val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        val nField: EditText = root.findViewById(R.id.nField)
        val kField: EditText = root.findViewById(R.id.kField)
        val startButton: Button = root.findViewById(R.id.button)
        startButton.setOnClickListener {
            val n = if (!nField.text.isNullOrBlank()) nField.text.toString().toInt() else 0
            val k = if (!kField.text.isNullOrBlank()) kField.text.toString().toInt() else 0
            if (k <= 50 && n <= 10000 && k > 0 && n > 0) {
                viewModel.n.value = n
                viewModel.k.value = k
                lifecycleScope.launch {
                    testing()
                }
            }
        }
        return root
    }

    private suspend fun generateKeys(n: Int, k: Int) = withContext(Dispatchers.Default) {
        return@withContext List(n) { List(k) { alphabet[Random.nextInt(alphabet.length)] }.toString() }
    }

    private suspend fun testing() {
        val itemList = generateKeys(viewModel.n.value!!, viewModel.k.value!!)
        val queryList =
            (itemList + generateKeys(viewModel.n.value!!, viewModel.k.value!!)).shuffled()
        var comparisonCount = 0L
        val hashStructure = HashStructure { comparisonCount++ }
        var result = testStructure(hashStructure, itemList, queryList)
        var foundCount = result.first
        var timeSpent = result.second
        hashViewModel.foundCount.value = foundCount
        hashViewModel.time.value = timeSpent
        hashViewModel.timeAverage.value = timeSpent / 2.0 / viewModel.n.value!!
        hashViewModel.comparisonCount.value = comparisonCount
        hashViewModel.comparisonAverage.value = comparisonCount / 3.0 / viewModel.n.value!!

        comparisonCount = 0L
        val treeStructure = TreeStructure { comparisonCount++ }
        result = testStructure(treeStructure, itemList, queryList)
        foundCount = result.first
        timeSpent = result.second
        treeViewModel.foundCount.value = foundCount
        treeViewModel.time.value = timeSpent
        treeViewModel.timeAverage.value = timeSpent / 2.0 / viewModel.n.value!!
        treeViewModel.comparisonCount.value = comparisonCount
        treeViewModel.comparisonAverage.value = comparisonCount / 3.0 / viewModel.n.value!!

        comparisonCount = 0L
        val sortedStructure = SortedStructure { comparisonCount++ }
        result = testStructure(sortedStructure, itemList, queryList)
        foundCount = result.first
        timeSpent = result.second
        sortedViewModel.foundCount.value = foundCount
        sortedViewModel.time.value = timeSpent
        sortedViewModel.timeAverage.value = timeSpent / 2.0 / viewModel.n.value!!
        sortedViewModel.comparisonCount.value = comparisonCount
        sortedViewModel.comparisonAverage.value = comparisonCount / 3.0 / viewModel.n.value!!

    }

    private suspend fun testStructure(
        structure: DataStructure,
        keys: List<String>,
        queries: List<String>
    ) =
        withContext(Dispatchers.Default) {
            structure.clear()
            var count = 0L
            val time = measureTimeMillis {
                for (key in keys) {
                    structure.add(key)
                }
                for (query in queries) {
                    count += if (structure.contains(query)) 1 else 0
                }
            }
            return@withContext count to time
        }
}
