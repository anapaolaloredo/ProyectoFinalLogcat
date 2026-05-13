package com.anapaolaloredo.proyectofinalprueba

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anapaolaloredo.proyectofinalprueba.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        // Si ya hay sesión activa, ir directo al Home
        if (viewModel.isLoggedIn()) {
            goToMain()
            return
        }

        setupTabs()
        observeViewModel()
    }

    private fun setupTabs() {
        // Toggle entre Login y Registro
        binding.btnTabLogin.setOnClickListener {
            binding.btnLogin.text = "Iniciar Sesión"
            // aquí puedes mostrar/ocultar campos extra si registro tiene más campos
        }
        binding.btnTabRegister.setOnClickListener {
            binding.btnLogin.text = "Registrarse"
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (binding.btnLogin.text == "Iniciar Sesión") {
                viewModel.login(username, password)
            } else {
                viewModel.register(username, password)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.authResult.observe(this) { state ->
            when (state) {
                is AuthState.Success -> goToMain()
                is AuthState.Registered -> {
                    Toast.makeText(this, "Cuenta creada, inicia sesión", Toast.LENGTH_SHORT).show()
                }
                is AuthState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish() // para que no puedan regresar al login con el botón atrás
    }
}