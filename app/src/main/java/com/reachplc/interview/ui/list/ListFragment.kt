package com.reachplc.interview.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentListBinding
import com.reachplc.interview.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel by viewModels<ListViewModel>()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        val adapter = ListAdapter()

        binding.apply {
            productListRecyclerView.setHasFixedSize(true)
            productListRecyclerView.adapter = adapter
        }

        viewModel.getProducts()
        viewModel.liveData.observe(viewLifecycleOwner, {
            adapter.differ.submitList(it.products)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}