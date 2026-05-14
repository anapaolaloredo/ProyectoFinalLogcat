package com.anapaolaloredo.proyectofinalprueba.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anapaolaloredo.proyectofinalprueba.data.AppDatabase
import com.anapaolaloredo.proyectofinalprueba.data.entity.Habit
import com.anapaolaloredo.proyectofinalprueba.data.remote.QuoteResponse
import com.anapaolaloredo.proyectofinalprueba.data.remote.RetrofitClient
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HabitRepository(context: Context) {
    private val habitDao = AppDatabase.getInstance(context).habitDao()

    fun obtenerHabitos(userId: Int): LiveData<List<Habit>> {
        return habitDao.obtenerHabitos(userId)
    }

    suspend fun agregarHabito(habit: Habit) {
        habitDao.insertar(habit)
    }

    suspend fun eliminarHabito(habit: Habit) {
        habitDao.eliminar(habit)
    }

    suspend fun completarHabito(habit: Habit) {
        val hoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        if (habit.ultimaFecha == hoy) return
        val actualizado = habit.copy(
            completadoHoy = true,
            racha = habit.racha + 1,
            xp = habit.xp + 10,
            ultimaFecha = hoy
        )
        habitDao.actualizar(actualizado)
    }

    suspend fun obtenerFrase(): QuoteResponse? {
        return try {
            RetrofitClient.zenQuotesApi.getQuote().firstOrNull()
        } catch (e: Exception) {
            null
        }
    }
}
