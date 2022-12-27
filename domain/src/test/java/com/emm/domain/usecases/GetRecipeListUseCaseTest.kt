package com.emm.domain.usecases

import com.emm.domain.fakerepositories.FakeRecipeRepository
import com.emm.domain.utils.ResultState
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetRecipeListUseCaseTest {

    private val fakeRecipeRepository = FakeRecipeRepository()

    private lateinit var getRecipeListUseCase: GetRecipeListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        getRecipeListUseCase = GetRecipeListUseCase(fakeRecipeRepository)
    }

    @Test
    fun `verify that the first value of the use case is correct`() = runTest {
        val inputParams = GetRecipeListUseCase.InputParams(
            page = 1,
        )
        val getRecipe = getRecipeListUseCase.invoke(inputParams)

        val firstValue = getRecipe.first()

        Truth.assertThat(firstValue).isInstanceOf(ResultState.Success::class.java)

        val response = (firstValue as ResultState.Success).data

        Truth.assertThat(response.data.size).isEqualTo(0)
        Truth.assertThat(response.page).isEqualTo(inputParams.page)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}