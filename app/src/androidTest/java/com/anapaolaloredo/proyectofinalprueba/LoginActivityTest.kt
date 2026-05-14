package com.anapaolaloredo.proyectofinalprueba

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun etUsername_estaVisible() {
        onView(withId(R.id.etUsername)).check(matches(isDisplayed()))
    }

    @Test
    fun etPassword_estaVisible() {
        onView(withId(R.id.etPassword)).check(matches(isDisplayed()))
    }

    @Test
    fun btnLogin_estaVisible() {
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
    }

    @Test
    fun btnTabRegister_cambiaTextoBoton() {
        onView(withId(R.id.btnTabRegister)).perform(click())
        onView(withId(R.id.btnLogin)).check(matches(withText("Registrarse")))
    }

    @Test
    fun btnTabLogin_muestraTextoIniciarSesion() {
        onView(withId(R.id.btnTabLogin)).perform(click())
        onView(withId(R.id.btnLogin)).check(matches(withText("Iniciar Sesión")))
    }
}
