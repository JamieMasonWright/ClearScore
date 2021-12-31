package com.jj.clearscore.data

import com.jj.clearscore.data.remote.dto.CreditReportDto
import retrofit2.http.GET

interface CreditAPI {

    //retrieve data from endpoint
    @GET("/endpoint.json")
    suspend fun fetchScore(): CreditReportDto

}