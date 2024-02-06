package com.sample.mycategory.presentation.category_list

import com.sample.common.Resource
import com.sample.domain.model.Category
import com.sample.domain.use_case.GetCategoriesUseCase
import com.sample.mycategory.BaseViewModelTest
import com.sample.mycategory.getDummyCategory
import com.sample.mycategory.runBlockingMainTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryListViewModelTest : BaseViewModelTest() {

    private lateinit var categoryListViewModel: CategoryListViewModel

    private val getCategoriesUseCase = mockk<GetCategoriesUseCase>()

    @Before
    fun setUp() {
        every { getCategoriesUseCase.invoke() } returns inputFlow

        categoryListViewModel =  CategoryListViewModel(getCategoriesUseCase)
    }

    @Test
    fun `Successful Result with List of Categories`() = runBlockingMainTest {


        every { getCategoriesUseCase.invoke() } returns inputFlow

        categoryListViewModel.getCategories()

        assertEquals(getDummyCategory(), categoryListViewModel.state.value.categories.first())
    }

    @Test
    fun `Error Result`() = runBlockingMainTest {
        val errorMessage = "An error occurred"
        val inputFlow: Flow<Resource<List<Category>>> =
            flowOf(Resource.Error(errorMessage))

        every { getCategoriesUseCase.invoke() } returns inputFlow

        categoryListViewModel.getCategories()

        assertEquals(errorMessage, categoryListViewModel.state.value.error)
        assertEquals(true, categoryListViewModel.state.value.isLoading)
        assertEquals(emptyList<Category>(), categoryListViewModel.state.value.categories)
    }

    @Test
    fun `Loading Result`() = runBlockingMainTest {
        val inputFlow: Flow<Resource<List<Category>>> =
            flowOf(Resource.Loading())

        every { getCategoriesUseCase.invoke() } returns inputFlow

        categoryListViewModel.getCategories()

        assertEquals(null, categoryListViewModel.state.value.error)
        assertEquals(true, categoryListViewModel.state.value.isLoading)
        assertEquals(emptyList<Category>(), categoryListViewModel.state.value.categories)
    }
    private companion object{
        val inputFlow: Flow<Resource<List<Category>>> =
            flowOf(Resource.Success(listOf(getDummyCategory())))
    }

}