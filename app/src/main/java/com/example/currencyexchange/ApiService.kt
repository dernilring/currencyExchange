package com.example.currencyexchange

import retrofit2.http.GET

interface ApiService {

    @GET("kursExchange")
    suspend fun getCurrency() : FilialResponse
}