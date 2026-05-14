package com.anapaolaloredo.proyectofinalprueba

sealed class AuthState {
    object Success : AuthState()
    object Registered : AuthState()
    data class Error(val message: String) : AuthState()
}
