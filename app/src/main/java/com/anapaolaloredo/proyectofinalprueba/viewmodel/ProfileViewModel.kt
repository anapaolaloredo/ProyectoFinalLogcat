package com.anapaolaloredo.proyectofinalprueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anapaolaloredo.proyectofinalprueba.data.entity.Habit
import com.anapaolaloredo.proyectofinalprueba.data.repository.AuthRepository
import com.anapaolaloredo.proyectofinalprueba.data.repository.HabitRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val habitRepository = HabitRepository(application)
    private val authRepository = AuthRepository(application)

    private val userId = authRepository.obtenerUserId()
    val username: String = authRepository.obtenerUsername()

    val habitos: LiveData<List<Habit>> = habitRepository.obtenerHabitos(userId)

    fun cerrarSesion() {
        authRepository.cerrarSesion()
    }
}
