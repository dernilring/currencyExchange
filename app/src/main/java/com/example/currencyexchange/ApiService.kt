package com.example.currencyexchange

import retrofit2.http.GET

interface ApiService {

    @GET("api/kursExchange")
    suspend fun getCurrency() : List<Filial>
}