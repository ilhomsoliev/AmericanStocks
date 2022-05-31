package com.ilhomsoliev.volgaitamericanstocks.di

import com.ilhomsoliev.volgaitamericanstocks.data.repository.RepositoryImpl
import com.ilhomsoliev.volgaitamericanstocks.data.remote.Api
import com.ilhomsoliev.volgaitamericanstocks.core.ApiConstants.BASE_URL
import com.ilhomsoliev.volgaitamericanstocks.core.ApiConstants.TOKEN
import com.ilhomsoliev.volgaitamericanstocks.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApiRepository(apiRequests: Api): Repository {
        return RepositoryImpl(api = apiRequests)
    }

    @Provides
    @Singleton
    fun provideApiRequests(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor{interceptor->
                val requets = interceptor.request()
                    .newBuilder()
                    .header("X-Finnhub-Token", TOKEN)
                    .build()
                interceptor.proceed(requets)
            }
            .addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
            ).build()
    }
}