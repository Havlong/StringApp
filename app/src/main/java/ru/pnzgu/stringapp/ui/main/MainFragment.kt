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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

    private suspend fun testing() = withContext(Dispatchers.Default) {
        val itemList = generateKeys(viewModel.n.value!!, viewModel.k.value!!)
        val queryList =
            (itemList + generateKeys(viewModel.n.value!!, viewModel.k.value!!)).shuffled()
        launch {
            var comparisonCount = 0L
            val hashStructure = HashStructure { comparisonCount++ }
            val result = testStructure(hashStructure, itemList, queryList)
            val foundCount = result.first
            val timeSpent = result.second
            withContext(Dispatchers.Main) {
                hashViewModel.foundCount.value = foundCount.toDouble()
                hashViewModel.time.value = timeSpent.toDouble()
                hashViewModel.timeAverage.value = timeSpent / 2.0 / viewModel.n.value!!
                hashViewModel.comparisonCount.value = comparisonCount
                hashViewModel.comparisonAverage.value = comparisonCount / 3.0 / viewModel.n.value!!
            }
        }

        launch {
            var comparisonCount = 0L
            val treeStructure = TreeStructure { comparisonCount++ }
            val result = testStructure(treeStructure, itemList, queryList)
            val foundCount = result.first
            val timeSpent = result.second
            withContext(Dispatchers.Main) {
                treeViewModel.foundCount.value = foundCount.toDouble()
                treeViewModel.time.value = timeSpent.toDouble()
                treeViewModel.timeAverage.value = timeSpent / 2.0 / viewModel.n.value!!
                treeViewModel.comparisonCount.value = comparisonCount
                treeViewModel.comparisonAverage.value = comparisonCount / 3.0 / viewModel.n.value!!
            }
        }

        launch {
            var comparisonCount = 0L
            val sortedStructure = SortedStructure { comparisonCount++ }
            val result = testStructure(sortedStructure, itemList, queryList)
            val foundCount = result.first
            val timeSpent = result.second
            withContext(Dispatchers.Main) {
                sortedViewModel.foundCount.value = foundCount.toDouble()
                sortedViewModel.time.value = timeSpent.toDouble()
                sortedViewModel.timeAverage.value = timeSpent / 2.0 / viewModel.n.value!!
                sortedViewModel.comparisonCount.value = comparisonCount
                sortedViewModel.comparisonAverage.value =
                    comparisonCount / 3.0 / viewModel.n.value!!
            }
        }
    }

    private suspend fun testStructure(
        structure: DataStructure, keys: List<String>, queries: List<String>
    ) = withContext(Dispatchers.Default) {
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
