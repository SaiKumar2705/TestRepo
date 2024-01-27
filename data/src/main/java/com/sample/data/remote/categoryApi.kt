package com.sample.data.remote

import com.sample.data.remote.dto.CategoryDto
import retrofit2.http.GET

internal interface categoryApi {
    @GET("categories")
    suspend fun getCategories(): CategoryDto
}