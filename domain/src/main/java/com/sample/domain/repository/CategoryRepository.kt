package com.sample.domain.repository

import com.sample.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}