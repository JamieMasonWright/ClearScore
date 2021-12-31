package com.jj.clearscore.data.repository

import com.jj.clearscore.data.remote.dto.CreditReportDto

interface ScoreRepository {

    //retrieve credit report
    suspend fun fetchCreditReport() : CreditReportDto

}