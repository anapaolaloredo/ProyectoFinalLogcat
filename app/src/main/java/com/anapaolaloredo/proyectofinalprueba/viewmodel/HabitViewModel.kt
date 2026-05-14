package com.anapaolaloredo.proyectofinalprueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anapaolaloredo.proyectofinalprueba.data.entity.Habit
import com.anapaolaloredo.proyectofinalprueba.data.remote.QuoteResponse
import com.anapaolaloredo.proyectofinalprueba.data.repository.AuthRepository
import com.anapaolaloredo.proyectofinalprueba.data.repository.HabitRepository
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val habitRepository = HabitRepository(application)
    private val authRepository = AuthRepository(application)

    private val userId = authRepository.obtenerUserId()
    val username: String = authRepository.obtenerUsername()

    val habitos: LiveData<List<Habit>> = habitRepository.obtenerHabitos(userId)

    private val _quote = MutableLiveData<QuoteResponse?>()
    val quote: LiveData<QuoteResponse?> = _quote

    init {
        cargarFrase()
    }

    fun cargarFrase() {
        viewModelScope.launch {
            _quote.value = habitRepository.obtenerFrase()
        }
    }

    fun agregarHabito(nombre: String, descripcion: String) {
        viewModelScope.launch {
            habitRepository.agregarHabito(
                Habit(userId = userId, nombre = nombre, descripcion = descripcion)
            )
        }
    }

    fun eliminarHabito(habit: Habit) {
        viewModelScope.launch {
            habitRepository.eliminarHabito(habit)
        }
    }

    fun completarHabito(habit: Habit) {
        viewModelScope.launch {
            habitRepository.completarHabito(habit)
        }
    }

    fun cerrarSesion() {
        authRepository.cerrarSesion()
    }
}
