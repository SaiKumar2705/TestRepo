package com.sample.data.di

import com.sample.data.repository.CategoryRepositoryImpl
import com.sample.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository
}