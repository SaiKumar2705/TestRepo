package com.sample.data

import com.sample.data.remote.dto.CategoryDto
import com.sample.domain.model.Category

fun getDummyCategory() = Category(
    categoryName = "Technology"
)

internal fun getDummyCategoryDto() = CategoryDto(
    count = 5,
    categories = listOf("Technology")
)