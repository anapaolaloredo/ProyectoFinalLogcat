package com.anapaolaloredo.proyectofinalprueba.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anapaolaloredo.proyectofinalprueba.data.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertar(user: User): Long

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun buscarPorUsername(username: String): User?
}
