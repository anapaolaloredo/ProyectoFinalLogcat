package com.anapaolaloredo.proyectofinalprueba

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anapaolaloredo.proyectofinalprueba.databinding.ActivityLoginBinding
import com.anapaolaloredo.proyectofinalprueba.viewmodel.AuthViewModel


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
        setTab(isLogin = true)

        binding.btnTabLogin.setOnClickListener { setTab(isLogin = true) }
        binding.btnTabRegister.setOnClickListener { setTab(isLogin = false) }

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

    private fun setTab(isLogin: Boolean) {
        if (isLogin) {
            binding.btnTabLogin.setBackgroundResource(R.drawable.bg_tab_selected)
            binding.btnTabLogin.setTextColor(0xFFFFFFFF.toInt())
            binding.btnTabRegister.setBackgroundResource(android.R.color.transparent)
            binding.btnTabRegister.setTextColor(0xFF8899BB.toInt())
            binding.btnLogin.text = "Iniciar Sesión"
        } else {
            binding.btnTabRegister.setBackgroundResource(R.drawable.bg_tab_selected)
            binding.btnTabRegister.setTextColor(0xFFFFFFFF.toInt())
            binding.btnTabLogin.setBackgroundResource(android.R.color.transparent)
            binding.btnTabLogin.setTextColor(0xFF8899BB.toInt())
            binding.btnLogin.text = "Registrarse"
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