package com.jj.clearscore.presentation.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jj.clearscore.common.Constants
import com.jj.clearscore.common.Resource
import com.jj.clearscore.domain.model.CreditScore
import com.jj.clearscore.domain.use_case.FetchScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel
@Inject
constructor(
    private val fetchScoreUseCase: FetchScoreUseCase
) : ViewModel() {


    //state of the score
    private val _state = MutableLiveData<ScoreState>()
    val state: LiveData<ScoreState>
        get() = _state

    init {
        fetchScore()
    }

    //invoke the fetch score use case
    fun fetchScore() {
        //call use case
        fetchScoreUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    //set values
                    _state.value = ScoreState(
                        creditScore = resource.data ?: CreditScore(0, 0)
                    )

                }
                is Resource.Error -> {
                    //set values
                    _state.value = ScoreState(
                        error = resource.message ?: Constants.UNEXPECTED
                    )
                }
                is Resource.Loading -> {
                    //set values
                    _state.value = ScoreState(
                        isLoading = true
                    )
                }

            }

        }.launchIn(viewModelScope)
    }
}