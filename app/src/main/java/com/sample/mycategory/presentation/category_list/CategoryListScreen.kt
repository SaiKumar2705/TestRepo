package com.sample.mycategory.presentation.category_list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun CategoryListScreen(viewModel: CategoryListViewModel) {
    val state by viewModel.state.collectAsState()

    Column {
        // Your Compose UI components go here
        CategoryList(state.categories)
    }
}