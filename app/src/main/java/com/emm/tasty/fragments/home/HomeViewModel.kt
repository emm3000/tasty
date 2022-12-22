package com.emm.tasty.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.domain.entitys.RecipeEntity
import com.emm.domain.usecases.GetRecipeListUseCase
import com.emm.domain.utils.ResultState
import com.emm.tasty.models.RecipeModel
import com.emm.tasty.models.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val getRecipeListUseCase: GetRecipeListUseCase
) : ViewModel() {

    private val _homeViewState = MutableStateFlow(HomeViewState())
    val homeViewState = _homeViewState.asStateFlow()

    val searchTextState = MutableStateFlow("")

    init {
        executeGetRecipeListUseCase()
        searchTextState
            .drop(1)
            .onEach(::onSearchTextChange)
            .launchIn(viewModelScope)
    }

    private fun executeGetRecipeListUseCase() {
        getRecipeListUseCase()
            .onStart { _homeViewState.update { it.copy(showLoading = true) } }
            .onEach { result -> _homeViewState.update { handleGetRecipeListUseCase(result) } }
            .launchIn(viewModelScope)
    }

    private fun onSearchTextChange(text: String) {
        if (text.isEmpty()) {
            _homeViewState.update { currentState ->
                currentState.copy(
                    recipeListFilter = currentState.originalRecipeList
                )
            }
            return
        }

        _homeViewState.update { currentState ->
            currentState.copy(
                recipeListFilter = currentState.originalRecipeList?.filter { it.id.toString() == text }
            )
        }
    }

    private fun handleGetRecipeListUseCase(result: ResultState<List<RecipeEntity>>): HomeViewState {
        return when (result) {
            is ResultState.Error -> {
                _homeViewState.value.copy(
                    showLoading = false,
                    errorMessage = result.message
                )
            }
            is ResultState.Success -> {
                val recipeListModel = result.data.toUIModel()
                _homeViewState.value.copy(
                    showLoading = false,
                    recipeListFilter = recipeListModel,
                    originalRecipeList = recipeListModel
                )
            }
        }
    }

}