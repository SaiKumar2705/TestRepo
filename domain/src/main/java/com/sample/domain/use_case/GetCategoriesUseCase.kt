package com.sample.domain.use_case

import com.sample.common.Resource
import com.sample.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
    operator fun invoke(): Flow<Resource<List<Category>>>
}