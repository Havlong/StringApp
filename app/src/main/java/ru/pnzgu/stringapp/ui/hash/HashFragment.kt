package ru.pnzgu.stringapp.ui.hash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.pnzgu.stringapp.R

class HashFragment : Fragment() {

    private lateinit var hashViewModel: HashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hashViewModel =
            ViewModelProvider(this).get(HashViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_hash, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        hashViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
