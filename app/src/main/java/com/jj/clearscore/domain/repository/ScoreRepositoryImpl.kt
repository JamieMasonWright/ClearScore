package com.jj.clearscore.domain.repository

import com.jj.clearscore.data.CreditAPI
import com.jj.clearscore.data.remote.dto.CreditReportDto
import com.jj.clearscore.data.repository.ScoreRepository
import javax.inject.Inject

class ScoreRepositoryImpl
@Inject constructor(private val api: CreditAPI) : ScoreRepository {

    override suspend fun fetchCreditReport(): CreditReportDto {
        return api.fetchScore()
    }
}
