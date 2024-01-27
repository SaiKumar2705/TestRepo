package com.sample.mycategory.presentation.beer_detail

import androidx.lifecycle.SavedStateHandle
import com.sample.common.Resource
import com.sample.mycategory.BaseViewModelTest
import com.sample.mycategory.getDummyBeer
import com.sample.mycategory.getDummyBeerDetails
import com.sample.mycategory.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BeerDetailViewModelTest : BaseViewModelTest() {

    private lateinit var beerDetailViewModel: BeerDetailViewModel

    @Mock
    lateinit var beerDetailsUseCase: GetBeerDetailsUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        beerDetailViewModel = BeerDetailViewModel(beerDetailsUseCase, savedStateHandle)
    }

    @Test
    fun `Successful Result with Detail of Beer`() = runBlockingMainTest {
        val inputFlow: Flow<Resource<BeerDetails>> =
            flowOf(Resource.Success(getDummyBeerDetails()))
        whenever(beerDetailsUseCase.invoke("1")).thenReturn(inputFlow)
        beerDetailViewModel.getBeer("1")
        assertEquals(getDummyBeer(), beerDetailViewModel.state.value.beer)
    }

}