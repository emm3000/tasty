package com.emm.tasty.fragments.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.domain.entitys.PlaceEntity
import com.emm.domain.usecases.GetMarkersUseCase
import com.emm.domain.utils.ResultState
import com.emm.tasty.constants.Constants.DEFAULT_LATITUDE
import com.emm.tasty.constants.Constants.DEFAULT_LONGITUDE
import com.emm.tasty.models.PlaceModel
import com.emm.tasty.models.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class MapViewModel(
    private val getMarkersUseCase: GetMarkersUseCase,
    private val keyword: String?
) : ViewModel() {

    private val _mapHomeViewState = MutableStateFlow(MapViewState())
    val mapHomeViewState = _mapHomeViewState.asStateFlow()

    private val _markerSelected = MutableStateFlow<PlaceModel?>(null)
    val markerSelected = _markerSelected.asStateFlow()

    init {
        executeGetMarkersUseCase()
    }

    fun executeGetMarkersUseCase() {
        val defaultLocation = "$DEFAULT_LATITUDE,$DEFAULT_LONGITUDE"

        getMarkersUseCase(keyword.orEmpty(), defaultLocation)
            .onStart { _mapHomeViewState.update { it.copy(showLoading = true) } }
            .onEach { result -> _mapHomeViewState.update { handleExecuteGetMarkersUseCase(result) } }
            .launchIn(viewModelScope)
    }

    private fun handleExecuteGetMarkersUseCase(
        resultState: ResultState<List<PlaceEntity>>
    ): MapViewState {
        return when (resultState) {
            is ResultState.Error -> {
                _mapHomeViewState.value.copy(
                    showLoading = false,
                    errorMessage = resultState.message
                )
            }
            is ResultState.Success -> {
                val placeList = resultState.data.toUIModel()
                _mapHomeViewState.value.copy(
                    places = placeList,
                    showLoading = false
                )
            }
        }
    }

    fun findPlaceOnMarkerSelected(placeId: String) {
        val placeModel: PlaceModel? = _mapHomeViewState.value.places?.find { it.placeId == placeId }
        _markerSelected.value = placeModel
    }

}