package com.emm.tasty.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.domain.entitys.RecipePaginationDataEntity
import com.emm.domain.usecases.GetRecipeListUseCase
import com.emm.domain.utils.ResultState
import com.emm.tasty.models.toUIModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

@OptIn(FlowPreview::class)
class HomeViewModel(
    private val getRecipeListUseCase: GetRecipeListUseCase
) : ViewModel() {

    private val _homeViewState = MutableStateFlow(HomeViewState())
    val homeViewState = _homeViewState.asStateFlow()

    val searchTextState = MutableStateFlow("").apply { collectTextChange() }

    init {
        getRecipeListPagination()
    }

    private fun Flow<String>.collectTextChange() {
        this.drop(1)
            .debounce(280L)
            .distinctUntilChanged()
            .onEach(::onSearchTextChange)
            .launchIn(viewModelScope)
    }

    private fun executeGetRecipeListUseCase(params: GetRecipeListUseCase.InputParams) {
        getRecipeListUseCase(params)
            .onStart { _homeViewState.update { it.copy(showLoading = true) } }
            .onEach { result -> _homeViewState.update { handleGetRecipeListUseCase(result, params.event) } }
            .launchIn(viewModelScope)
    }

    fun loadMoreRecipesFromScroll() {
        val hasNext = _homeViewState.value.paginationInfo?.hasNext ?: true
        if (!hasNext) return

        val page = _homeViewState.value.paginationInfo?.page ?: -1
        val params = GetRecipeListUseCase.InputParams(
            page = page.inc(),
            filter = searchTextState.value,
            event = GetRecipeListUseCase.Event.ON_SCROLL
        )

        executeGetRecipeListUseCase(params)
    }

    fun getRecipeListPagination() {
        val params = GetRecipeListUseCase.InputParams(
            page = 0,
        )
        executeGetRecipeListUseCase(params)

    }

    private fun onSearchTextChange(searchText: String) {
        val params = GetRecipeListUseCase.InputParams(
            page = 0,
            filter = searchText,
            event = GetRecipeListUseCase.Event.ON_TEXT_CHANGE
        )
        executeGetRecipeListUseCase(params)
    }

    private fun handleGetRecipeListUseCase(
        result: ResultState<RecipePaginationDataEntity>,
        event: GetRecipeListUseCase.Event
    ): HomeViewState {
        return when (result) {
            is ResultState.Error -> {
                _homeViewState.value.copy(
                    showLoading = false,
                    errorMessage = result.message
                )
            }
            is ResultState.Success -> {
                val recipeListModel = result.data.data.toUIModel()
                _homeViewState.value.copy(
                    showLoading = false,
                    recipeList = when (event) {
                        GetRecipeListUseCase.Event.ON_SCROLL -> (_homeViewState.value.recipeList ?: emptyList()) + recipeListModel
                        GetRecipeListUseCase.Event.ON_TEXT_CHANGE -> recipeListModel
                        GetRecipeListUseCase.Event.NONE -> recipeListModel
                    },
                    paginationInfo = result.data.toUIModel()
                )
            }
        }
    }

}