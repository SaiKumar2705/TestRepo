package com.sample.mycategory.presentation.category_list

import com.sample.domain.model.Category

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)
