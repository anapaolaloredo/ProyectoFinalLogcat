package com.anapaolaloredo.proyectofinalprueba

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HabitUtilsTest {

    @Test
    fun calcularXp_racha0_retorna0() {
        val result = HabitUtils.calcularXp(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun calcularXp_racha5_retorna50() {
        val result = HabitUtils.calcularXp(5)
        assertThat(result).isEqualTo(50)
    }

    @Test
    fun esRachaGenerosa_racha7_retornaTrue() {
        val result = HabitUtils.esRachaGenerosa(7)
        assertThat(result).isTrue()
    }

    @Test
    fun esRachaGenerosa_racha6_retornaFalse() {
        val result = HabitUtils.esRachaGenerosa(6)
        assertThat(result).isFalse()
    }

    @Test
    fun calcularPorcentaje_2de4_retorna50() {
        val result = HabitUtils.calcularPorcentaje(2, 4)
        assertThat(result).isEqualTo(50)
    }

    @Test
    fun calcularPorcentaje_totalCero_retorna0() {
        val result = HabitUtils.calcularPorcentaje(3, 0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun calcularPorcentaje_todosCompletados_retorna100() {
        val result = HabitUtils.calcularPorcentaje(5, 5)
        assertThat(result).isEqualTo(100)
    }

    @Test
    fun esHabitoValido_nombreVacio_retornaFalse() {
        val result = HabitUtils.esHabitoValido("")
        assertThat(result).isFalse()
    }

    @Test
    fun esHabitoValido_nombreValido_retornaTrue() {
        val result = HabitUtils.esHabitoValido("Ejercicio")
        assertThat(result).isTrue()
    }

    @Test
    fun calcularNivel_xp150_retorna1() {
        val result = HabitUtils.calcularNivel(150)
        assertThat(result).isEqualTo(1)
    }
}
