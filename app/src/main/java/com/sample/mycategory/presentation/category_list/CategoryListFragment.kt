package com.sample.mycategory.presentation.category_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sample.mycategory.databinding.FragmentCategoryListBinding
import com.sample.mycategory.presentation.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private val viewModel by viewModels<CategoryListViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCategoryListBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        categoryAdapter = CategoryAdapter()
        binding.rvBeers.adapter = categoryAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter.setOnItemClickListener {

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModel.state.collect { value: CategoryListState ->

                value.categories.let {
                    categoryAdapter.differ.submitList(it)
                }
            }
        }

    }

}