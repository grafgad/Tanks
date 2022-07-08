package com.example.tanks.di

import com.example.tanks.BuildConfig
import com.example.tanks.di.service_locator.ApiDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val baseURL = "https://api.worldoftanks.ru"

@Module
class NetworkModule {

    @Provides
    fun provideApiDataSource(retrofit: Retrofit): ApiDataSource {
        return retrofit.create(ApiDataSource::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(ClanApiInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        return client.build()
    }
}

object ClanApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("application_id", BuildConfig.APPLICATION_ID)
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}