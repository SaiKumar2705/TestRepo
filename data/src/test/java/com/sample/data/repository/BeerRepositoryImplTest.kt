package com.sample.data.repository

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
class BeerRepositoryImplTest {

    private lateinit var beerRepositoryImpl: CategoryRepositoryImpl
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    internal lateinit var categoryApi: categoryApi

    @Before
    fun setUp() {
        Dispatchers.
        setMain(mainThreadSurrogate)
        beerRepositoryImpl = CategoryRepositoryImpl(categoryApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

//    @Test
//    fun `Get list of beers`() = runBlocking {
//        whenever(categoryApi.getCategories()).thenReturn(listOf(getDummyBeerDto()))
//        val beerList = beerRepositoryImpl.getCategories()
//        assertEquals(listOf(getDummyBeer()), beerList)
//    }

}