package com.sample.domain.use_case

import com.sample.common.CategoriesError
import com.sample.common.Constants.ERROR_MESSAGE
import com.sample.common.Resource
import com.sample.domain.model.Category
import com.sample.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class GetCategoriesUseCaseImpl @Inject constructor(private val repository: CategoryRepository) :
    GetCategoriesUseCase {

    override operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = repository.getCategories()
            emit(Resource.Success(categories))
        } catch (e: HttpException) {
            handleException(e)
        }
    }


    private fun handleException(exception: Exception): CategoriesError {
        return when (exception) {
            is HttpException -> CategoriesError.Http(
                exception.localizedMessage ?: ERROR_MESSAGE
            )
            is IOException -> CategoriesError.Network
            else -> CategoriesError.Unexpected
        }
    }
}
