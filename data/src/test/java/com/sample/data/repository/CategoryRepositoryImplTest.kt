package com.sample.data.repository

import com.sample.data.getDummyCategory
import com.sample.data.getDummyCategoryDto
import com.sample.data.remote.categoryApi
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryImplTest {

    private lateinit var categoryRepositoryImpl: CategoryRepositoryImpl
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    internal lateinit var categoryApi: categoryApi

    @Before
    fun setUp() {
        Dispatchers.
        setMain(mainThreadSurrogate)
        categoryRepositoryImpl = CategoryRepositoryImpl(categoryApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `Get list of categories`() = runBlocking {
        whenever(categoryApi.getCategories()).thenReturn(getDummyCategoryDto())
        val categoryList = categoryRepositoryImpl.getCategories()
        assertEquals(listOf(getDummyCategory()), categoryList)
    }

}