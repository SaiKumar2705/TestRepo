package com.sample.mycategory.presentation.category_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.common.Resource
import com.sample.domain.use_case.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getBeersUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryListState())
    val state: StateFlow<CategoryListState> = _state

    init {
        getCategories()
    }

     fun getCategories() {
        getBeersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CategoryListState(categories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CategoryListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CategoryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}