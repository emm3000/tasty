package com.emm.tasty.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.domain.entitys.UserEntity
import com.emm.domain.usecases.GetUserUseCase
import com.emm.domain.utils.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(UserEntity())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    init {
        getUserUseCase.invoke()
            .onStart { _viewStateFlow.update { it.copy(isLoading = true) } }
            .onEach(::handleResultState)
            .launchIn(viewModelScope)
    }

    private fun handleResultState(res: ResultState<UserEntity>) {
        when (res) {
            is ResultState.Error -> {}
            is ResultState.Success -> {
                _viewStateFlow.update { res.data }
            }
        }
    }

}