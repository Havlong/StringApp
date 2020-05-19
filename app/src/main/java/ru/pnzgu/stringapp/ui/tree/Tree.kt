package ru.pnzgu.stringapp.ui.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.pnzgu.stringapp.R

class Tree : Fragment() {

    private lateinit var treeViewModel: TreeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        treeViewModel =
            ViewModelProvider(this).get(TreeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tree, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        treeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
