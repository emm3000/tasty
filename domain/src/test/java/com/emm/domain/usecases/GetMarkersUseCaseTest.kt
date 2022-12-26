package com.emm.domain.usecases

import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.fakedata.FakeResponse.FAKE_PLACES_LIST
import com.emm.domain.fakerepositories.FakePlaceApiRepository
import com.emm.domain.utils.ResultState
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMarkersUseCaseTest {
    private val fakePlaceApiRepository = FakePlaceApiRepository()

    private lateinit var getMarkersUseCase: GetMarkersUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        getMarkersUseCase = GetMarkersUseCase(fakePlaceApiRepository)
    }

    @Test
    fun `verify that the first value of the use case is correct`() = runTest {
        val apiPlace = getMarkersUseCase.invoke("keyword", "random")

        val firstValue: ResultState<List<PlaceEntity>> = apiPlace.first()

        assertThat(firstValue).isEqualTo(
            ResultState.Success(FAKE_PLACES_LIST)
        )
        assertThat(firstValue).isInstanceOf(ResultState.Success::class.java)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}