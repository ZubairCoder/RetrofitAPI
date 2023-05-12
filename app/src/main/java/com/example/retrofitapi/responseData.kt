package com.example.retrofitapi

data class responseData(
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
) {
}