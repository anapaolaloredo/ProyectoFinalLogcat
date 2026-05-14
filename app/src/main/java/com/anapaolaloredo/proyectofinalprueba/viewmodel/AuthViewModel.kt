package com.anapaolaloredo.proyectofinalprueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anapaolaloredo.proyectofinalprueba.AuthState
import com.anapaolaloredo.proyectofinalprueba.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository(application)

    private val _authResult = MutableLiveData<AuthState>()
    val authResult: LiveData<AuthState> = _authResult

    fun isLoggedIn(): Boolean = repository.isLoggedIn()

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _authResult.value = AuthState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            val user = repository.login(username, password)
            if (user != null) {
                repository.guardarSesion(user.id, user.username)
                _authResult.value = AuthState.Success
            } else {
                _authResult.value = AuthState.Error("Usuario o contraseña incorrectos")
            }
        }
    }

    fun register(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _authResult.value = AuthState.Error("Completa todos los campos")
            return
        }
        viewModelScope.launch {
            val registrado = repository.register(username, password)
            if (registrado) {
                _authResult.value = AuthState.Registered
            } else {
                _authResult.value = AuthState.Error("El usuario ya existe")
            }
        }
    }
}
