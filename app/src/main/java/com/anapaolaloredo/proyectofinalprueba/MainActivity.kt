package com.anapaolaloredo.proyectofinalprueba

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anapaolaloredo.proyectofinalprueba.databinding.ActivityMainBinding
import com.anapaolaloredo.proyectofinalprueba.ui.AddHabitFragment
import com.anapaolaloredo.proyectofinalprueba.ui.HabitAdapter
import com.anapaolaloredo.proyectofinalprueba.viewmodel.HabitViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HabitViewModel
    private lateinit var adapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]

        setupRecyclerView()
        observeViewModel()
        setupListeners()
        mostrarFechaYSaludo()
    }

    private fun setupRecyclerView() {
        adapter = HabitAdapter(
            onComplete = { habit -> viewModel.completarHabito(habit) },
            onDelete = { habit -> viewModel.eliminarHabito(habit) }
        )
        binding.rvHabits.layoutManager = LinearLayoutManager(this)
        binding.rvHabits.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.habitos.observe(this) { habitos ->
            adapter.submitList(habitos)

            val total = habitos.size
            val completados = habitos.count { it.completadoHoy }
            val rachaMax = habitos.maxOfOrNull { it.racha } ?: 0
            val porcentaje = HabitUtils.calcularPorcentaje(completados, total)

            binding.tvStreak.text = rachaMax.toString()
            binding.tvTodayProgress.text = "$completados/$total"
            binding.tvPercent.text = "$porcentaje%"
            binding.cardEmpty.visibility = if (habitos.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.quote.observe(this) { quote ->
            quote?.let {
                binding.tvQuote.text = "\"${it.text}\""
                binding.tvAuthor.text = "— ${it.author}"
            }
        }
    }

    private fun setupListeners() {
        binding.btnAddHabit.setOnClickListener {
            AddHabitFragment().show(supportFragmentManager, "AddHabit")
        }

        binding.btnLogout.setOnClickListener {
            viewModel.cerrarSesion()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun mostrarFechaYSaludo() {
        val sdf = SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", Locale("es", "MX"))
        binding.tvDate.text = sdf.format(Date())
        binding.tvGreeting.text = "¡Hola, ${viewModel.username}!"
    }
}
