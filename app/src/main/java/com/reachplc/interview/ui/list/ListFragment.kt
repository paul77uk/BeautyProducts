package com.reachplc.interview.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel by viewModels<ListViewModel>()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Beauty Products"
        _binding = FragmentListBinding.bind(view)

        val adapter = ListAdapter()

        adapter.setOnItemClickListener {
//            val action = ListFragmentDirections.actionFragmentListToDetailsFragment()
            val bundle = Bundle().apply {
                putSerializable("selected_product", it)
            }
            findNavController().navigate(
                R.id.action_fragmentList_to_detailsFragment,
                        bundle
            )
        }

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