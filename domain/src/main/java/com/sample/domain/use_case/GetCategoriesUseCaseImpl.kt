package com.sample.domain.use_case

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
            val beers = repository.getCategories()
            emit(Resource.Success(beers))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}