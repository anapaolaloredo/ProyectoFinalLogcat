package com.anapaolaloredo.proyectofinalprueba.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.anapaolaloredo.proyectofinalprueba.data.entity.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits WHERE userId = :userId")
    fun obtenerHabitos(userId: Int): LiveData<List<Habit>>

    @Insert
    suspend fun insertar(habit: Habit)

    @Update
    suspend fun actualizar(habit: Habit)

    @Delete
    suspend fun eliminar(habit: Habit)
}
