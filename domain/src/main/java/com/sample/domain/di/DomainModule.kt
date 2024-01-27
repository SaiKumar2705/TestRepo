package com.sample.domain.di

import com.sample.domain.use_case.GetCategoriesUseCase
import com.sample.domain.use_case.GetCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DomainModule {
    @Binds
    @Singleton
    internal abstract fun bindGetCategoriesUseCase(useCaseImpl: GetCategoriesUseCaseImpl): GetCategoriesUseCase
}