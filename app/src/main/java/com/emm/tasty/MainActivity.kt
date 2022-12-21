package com.emm.tasty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.emm.data.testfunctions.RandomClass
import com.emm.domain.entitys.UserModel
import com.emm.domain.usecases.GetUserUseCase
import com.emm.domain.utils.ResultState
import com.emm.utils.collectInViews
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        collectInViews {
            vm.viewStateFlow.collect {
                findViewById<TextView>(R.id.perro).text = if (it.isLoading) {
                    "Cargando . . ."
                } else {
                    it.title
                }
            }
        }
    }

}

class MainViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(UserModel())
    val viewStateFlow = _viewStateFlow.asStateFlow()

    init {
        getUserUseCase.invoke()
            .onStart { _viewStateFlow.update { it.copy(isLoading = true) } }
            .onEach(::handleResultState)
            .launchIn(viewModelScope)
    }

    private fun handleResultState(res: ResultState<UserModel>) {
        when (res) {
            is ResultState.Error -> {}
            is ResultState.Success -> {
                _viewStateFlow.update { res.data }
            }
        }
    }

}