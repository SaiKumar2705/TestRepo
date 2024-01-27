package com.sample.mycategory.presentation.category_list

import com.sample.common.Resource
import com.sample.domain.model.Category
import com.sample.domain.use_case.GetCategoriesUseCase
import com.sample.mycategory.BaseViewModelTest
import com.sample.mycategory.getDummyCategory
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
class CategoryListViewModelTest : BaseViewModelTest() {

    private lateinit var beerListViewModel: CategoryListViewModel

    @Mock
    lateinit var getBeersUseCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        beerListViewModel =  CategoryListViewModel(getBeersUseCase)
    }

    @Test
    fun `Successful Result with List of Categories`() = runBlockingMainTest {
        val inputFlow: Flow<Resource<List<Category>>> =
            flowOf(Resource.Success(listOf(getDummyCategory())))
        whenever(getBeersUseCase.invoke()).thenReturn(inputFlow)
        beerListViewModel.getCategories()
        assertEquals(getDummyCategory(), beerListViewModel.state.value.categories.first())
    }

}