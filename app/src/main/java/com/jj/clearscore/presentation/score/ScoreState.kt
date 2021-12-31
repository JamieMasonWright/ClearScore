package com.jj.clearscore.presentation.score

import com.jj.clearscore.domain.model.CreditScore

data class ScoreState(
    val isLoading: Boolean = false,
    val creditScore: CreditScore? = null,
    val error: String = ""
)