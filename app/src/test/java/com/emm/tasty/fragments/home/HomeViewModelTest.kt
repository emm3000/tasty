package com.emm.tasty.fragments.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emm.domain.entitys.RecipePaginationDataEntity
import com.emm.domain.usecases.GetRecipeListUseCase
import com.emm.domain.utils.ResultState
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @RelaxedMockK lateinit var getRecipeListUseCase: GetRecipeListUseCase


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        homeViewModel = HomeViewModel(getRecipeListUseCase)
    }

    @Test
    fun `check how the state flow values vary`() = runTest {
        coEvery { getRecipeListUseCase(any()) } returns flowOf(ResultState.Success(buildFakeRecipePaginationDataEntity()))

        homeViewModel.getRecipeListPagination()

        delay(2000L)

        assertThat(homeViewModel.homeViewState.value.recipeList!!.size).isEqualTo(0)
        assertThat(homeViewModel.homeViewState.value.paginationInfo?.total)
            .isEqualTo(1)

    }

    @Test
    fun `verify the change of the input`() = runTest {
        coEvery { getRecipeListUseCase(any()) } returns flowOf(ResultState.Success(buildFakeRecipePaginationDataEntity()))

        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            homeViewModel.searchTextState.collect()
        }

        assertThat(homeViewModel.searchTextState.value).isEqualTo("")

        homeViewModel.searchTextState.value = "Expected"

        delay(1000L)
        homeViewModel.searchTextState.value = "Expected2"
        delay(2000L)

        coVerify(exactly = 2) {
            getRecipeListUseCase.invoke(any())
        }
        collectJob.cancel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun buildFakeRecipePaginationDataEntity(): RecipePaginationDataEntity {
        return RecipePaginationDataEntity(
            data = emptyList(),
            total = 1,
            page = 1,
            limit = 1,
            pages = 1,
            hasNext = false,
            hasPrev = false
        )
    }

}