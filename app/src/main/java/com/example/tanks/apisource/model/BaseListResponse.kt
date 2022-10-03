package com.example.tanks.apisource.model

interface BaseListResponse<T : Any> {
    val status: String
    val data: List<T>
}