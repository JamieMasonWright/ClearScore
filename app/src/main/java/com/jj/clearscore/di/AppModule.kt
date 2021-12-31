package com.jj.clearscore.di


import com.jj.clearscore.common.Constants
import com.jj.clearscore.data.CreditAPI
import com.jj.clearscore.data.repository.ScoreRepository
import com.jj.clearscore.domain.repository.ScoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideCreditApi() : CreditAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CreditAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideScoreRepository(api: CreditAPI) : ScoreRepository {
        return ScoreRepositoryImpl(api)
    }
}
//the dependency injection module