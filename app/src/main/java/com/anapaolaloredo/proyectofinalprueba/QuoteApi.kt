package com.anapaolaloredo.proyectofinalprueba

import retrofit2.http.GET

// 🔹 API
interface QuoteApi {
    @GET("today")
    suspend fun getQuote(): List<Quote>
}
