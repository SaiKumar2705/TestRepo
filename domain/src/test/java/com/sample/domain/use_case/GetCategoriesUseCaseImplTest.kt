package com.sample.domain.use_case

import com.sample.domain.getDummyCategory
import com.sample.domain.model.Category
import com.sample.domain.repository.CategoryRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCategoriesUseCaseImplTest {

    private lateinit var getCategoriesUseCaseImpl: GetCategoriesUseCaseImpl
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var repository: CategoryRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        getCategoriesUseCaseImpl = GetCategoriesUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `Successful Result with List of Categories`() {
        runBlocking {
            whenever(repository.getCategories()).thenReturn(listOf(getDummyCategory()))
            var category: Category? = null
            val output = getCategoriesUseCaseImpl.invoke()
            output.collect {
                category = it.data?.first()
            }

            assertEquals(getDummyCategory(), category)
        }
    }
}

