package com.reachplc.interview.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentListBinding
import com.reachplc.interview.util.Resource
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
            buttonRetry.setOnClickListener {
                viewModel.getProducts()
            }
        }

        viewModel.getProducts()
        viewModel.resourceResponse.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.differ.submitList(resource.data?.products)
                    binding.productListRecyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.buttonRetry.visibility = View.GONE
                    binding.textViewError.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.buttonRetry.visibility = View.VISIBLE
                    binding.textViewError.visibility = View.VISIBLE
                    binding.productListRecyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.buttonRetry.visibility = View.GONE
                    binding.textViewError.visibility = View.GONE
                    binding.productListRecyclerView.visibility = View.GONE
                }
            }

        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}