package com.example.tanks

import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ClansApi {
    private const val baseURL = "https://api.worldoftanks.ru"

    fun getClansInfo() {
        val clansRetrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        return client.build()
    }
    //https://api.worldoftanks.ru/wot/clans/list/?application_id=56a4a330cbb2a8afa118d74e08ea8f32&limit=1


//    @GET("wot/clans/list/?application_id=${BuildConfig.APPLICATION_ID}&limit=5")
//    fun getClanList(): Single<ClanResponse> {
//    }
}
    object ClanApiInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
            chain.request()
                .newBuilder()
                .header("application_id", BuildConfig.APPLICATION_ID)
                .build()
        )


    }