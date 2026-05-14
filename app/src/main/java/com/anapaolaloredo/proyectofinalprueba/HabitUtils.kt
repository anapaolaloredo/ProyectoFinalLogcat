package com.anapaolaloredo.proyectofinalprueba

object HabitUtils {
    fun calcularXp(racha: Int): Int = racha * 10

    fun esRachaGenerosa(racha: Int): Boolean = racha >= 7

    fun calcularPorcentaje(completados: Int, total: Int): Int {
        if (total == 0) return 0
        return (completados * 100) / total
    }

    fun esHabitoValido(nombre: String): Boolean = nombre.isNotBlank()

    fun calcularNivel(xp: Int): Int = xp / 100
}
