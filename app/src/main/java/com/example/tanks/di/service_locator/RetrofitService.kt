package com.example.tanks.di.service_locator

import com.example.tanks.BuildConfig
import com.example.tanks.di.ClanInfoDeserializer
import com.example.tanks.di.PlayerInfoDeserializer
import com.example.tanks.model.claninfo.ClanInfo
import com.example.tanks.model.playerinfo.PlayerInfo
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val baseURL = "https://api.worldoftanks.ru"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                            //десериализатор для получения данных игрока
                        .registerTypeAdapter(PlayerInfo::class.java, PlayerInfoDeserializer())
                        .registerTypeAdapter(ClanInfo::class.java, ClanInfoDeserializer())
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

//    Использовался в сервис-локаторе
//    fun getApiDataSource(): ApiDataSource {
//        return retrofit.create(ApiDataSource::class.java)
//    }

    private fun createOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(ClanApiInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        return client.build()
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
}

