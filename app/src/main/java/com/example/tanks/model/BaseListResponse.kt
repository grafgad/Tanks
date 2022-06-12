package com.example.tanks.model

interface BaseListResponse<T : Any> {
    val status: String
    val data: List<T>
}