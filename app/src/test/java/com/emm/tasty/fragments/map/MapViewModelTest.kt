package com.emm.tasty.fragments.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.usecases.GetMarkersUseCase
import com.emm.domain.utils.ResultState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MapViewModelTest {

    private lateinit var mapViewModel: MapViewModel

    @RelaxedMockK lateinit var getMarkersUseCase: GetMarkersUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        mapViewModel = MapViewModel(getMarkersUseCase, "keyword")
    }

    @Test
    fun `verify the correct mapping of the list of places`() = runTest {
        coEvery { getMarkersUseCase(any(), any()) } returns flowOf(buildSuccessResultState())

        mapViewModel.executeGetMarkersUseCase()
        delay(200L)

        assertThat(mapViewModel.mapHomeViewState.value.places).isNotNull()
    }

    @Test
    fun `check the error message when the use case returns error`() = runTest {
        coEvery { getMarkersUseCase(any(), any()) } returns flowOf(buildErrorResultState())

        mapViewModel.executeGetMarkersUseCase()

        delay(200L)

        assertThat(mapViewModel.mapHomeViewState.value.places).isNull()
        assertThat(mapViewModel.mapHomeViewState.value.errorMessage).isEqualTo("Error message")
    }

    @Test
    fun `verify the action of selecting a marker on the map, and there is a place`() = runTest {
        coEvery { getMarkersUseCase(any(), any()) } returns flowOf(buildSuccessResultState())

        val expectedPlaceId = "1"

        mapViewModel.executeGetMarkersUseCase()

        delay(200L)

        mapViewModel.findPlaceOnMarkerSelected(expectedPlaceId)

        assertThat(mapViewModel.markerSelected.value).isNotNull()
        assertThat(mapViewModel.markerSelected.value?.placeId).isEqualTo(expectedPlaceId)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun buildErrorResultState() = ResultState.Error(error = NullPointerException(), message = "Error message")

    private fun buildSuccessResultState() = ResultState.Success(buildFakeMarkersListByKeyword())

    private fun buildFakeMarkersListByKeyword(): List<PlaceEntity> {
        return listOf(
            PlaceEntity(
                latitude = 0.0,
                longitude = 0.0,
                photoReference = "",
                name = "",
                street = "",
                placeId = "1"
            ),
            PlaceEntity(
                latitude = 0.0,
                longitude = 0.0,
                photoReference = "",
                name = "",
                street = "",
                placeId = "2"
            ),
        )
    }
}