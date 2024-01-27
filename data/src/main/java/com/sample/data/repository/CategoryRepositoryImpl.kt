package com.sample.data.repository

import com.sample.data.remote.categoryApi
import com.sample.domain.model.Category
import com.sample.domain.repository.CategoryRepository
import javax.inject.Inject

internal class CategoryRepositoryImpl@Inject constructor(private val categoryApi: categoryApi):
    CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        val categoryDto = categoryApi.getCategories()
        return categoryDto.categories.map { Category(categoryName = it) }
    }
}