package ru.pnzgu.stringapp.ui.sorted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.pnzgu.stringapp.R

class SortedFragment : Fragment() {

    private lateinit var sortedViewModel: SortedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sortedViewModel =
            ViewModelProvider(this).get(SortedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sorted, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        sortedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
