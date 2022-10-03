package com.example.tanks.apisource.model

interface BaseDetailResponse<T : Any> {
    val status: String
    val data: T
}