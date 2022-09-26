package com.example.tanks.model

interface BaseDetailResponse<T : Any> {
    val status: String
    val data: T
}