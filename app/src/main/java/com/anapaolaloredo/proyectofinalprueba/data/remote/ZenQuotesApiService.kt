package com.anapaolaloredo.proyectofinalprueba.data.remote

import retrofit2.http.GET

interface ZenQuotesApiService {
    @GET("today")
    suspend fun getQuote(): List<QuoteResponse>
}
