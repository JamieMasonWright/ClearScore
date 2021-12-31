package com.jj.clearscore.domain.use_case

import com.jj.clearscore.common.Constants
import com.jj.clearscore.common.Resource
import com.jj.clearscore.data.remote.dto.toCreditScore
import com.jj.clearscore.data.repository.ScoreRepository
import com.jj.clearscore.domain.model.CreditScore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchScoreUseCase
@Inject constructor(private val repository: ScoreRepository){

    operator fun invoke() : Flow<Resource<CreditScore>> = flow {

        try {
            //start loading
            emit(Resource.Loading<CreditScore>())
            //get score from repo
            val score = repository.fetchCreditReport().creditReportInfo.toCreditScore()
            //success
            emit(Resource.Success<CreditScore>(score))

        }catch (e: HttpException){
            //unexpected error
            emit(Resource.Error<CreditScore>(e.localizedMessage ?: Constants.UNEXPECTED))
        }catch (e: IOException){
            //network error
            emit(Resource.Error<CreditScore>(Constants.NETWORK_IO_ERROR))
        }

    }

}