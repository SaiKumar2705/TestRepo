package com.sample.mycategory.presentation.category_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.domain.model.Category

@Composable
fun CategoryItem(category: Category) {
    // Your Compose UI components for each category item go here
    Text(
        text = category.categoryName,
        modifier = Modifier.padding(8.dp),
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    )
}