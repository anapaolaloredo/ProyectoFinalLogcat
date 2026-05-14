package com.anapaolaloredo.proyectofinalprueba.data.repository

import android.content.Context
import com.anapaolaloredo.proyectofinalprueba.data.AppDatabase
import com.anapaolaloredo.proyectofinalprueba.data.entity.User

class AuthRepository(context: Context) {
    private val userDao = AppDatabase.getInstance(context).userDao()
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }

    suspend fun register(username: String, password: String): Boolean {
        val existe = userDao.buscarPorUsername(username)
        if (existe != null) return false
        userDao.insertar(User(username = username, password = password))
        return true
    }

    fun guardarSesion(userId: Int, username: String) {
        prefs.edit()
            .putInt("userId", userId)
            .putString("username", username)
            .apply()
    }

    fun obtenerUserId(): Int = prefs.getInt("userId", -1)

    fun obtenerUsername(): String = prefs.getString("username", "Usuario") ?: "Usuario"

    fun isLoggedIn(): Boolean = prefs.getInt("userId", -1) != -1

    fun cerrarSesion() {
        prefs.edit().clear().apply()
    }
}
