package com.anapaolaloredo.proyectofinalprueba

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvQuote = findViewById<TextView>(R.id.tvQuote)
        val tvAuthor = findViewById<TextView>(R.id.tvAuthor)

        // 🔹 Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(QuoteApi::class.java)

        // 🔥 llamada directa
        lifecycleScope.launch {
            try {
                val response = api.getQuote()
                val quote = response.firstOrNull()

                quote?.let {
                    tvQuote.text = it.text
                    tvAuthor.text = it.author
                }

            } catch (e: Exception) {
                tvQuote.text = "Error al cargar"
            }
        }
    }
}