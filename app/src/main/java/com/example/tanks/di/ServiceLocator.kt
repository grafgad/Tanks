package com.example.tanks.di

object ServiceLocator {

    val retrofitService =  RetrofitService
    val apiDataSource: ApiDataSource = retrofitService.getApiDataSource()

}