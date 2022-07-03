package com.example.tanks.di

import com.example.tanks.ApiDataSource

object ServiceLocator {

    val apiDataSource: ApiDataSource = Api.getApiDataSource()


}