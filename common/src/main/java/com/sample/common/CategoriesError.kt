package com.sample.common

sealed class CategoriesError {
    data class Http(val message: String?) : CategoriesError()
    object Network : CategoriesError()
    object Unexpected : CategoriesError()
}