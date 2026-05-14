package com.anapaolaloredo.proyectofinalprueba.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val nombre: String,
    val descripcion: String,
    val racha: Int = 0,
    val completadoHoy: Boolean = false,
    val xp: Int = 0,
    val ultimaFecha: String = ""
)
