package com.anapaolaloredo.proyectofinalprueba.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.anapaolaloredo.proyectofinalprueba.LoginActivity
import com.anapaolaloredo.proyectofinalprueba.databinding.FragmentProfileBinding
import com.anapaolaloredo.proyectofinalprueba.viewmodel.HabitViewModel
import com.anapaolaloredo.proyectofinalprueba.viewmodel.ProfileViewModel


class ProfileFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val habitViewModel: HabitViewModel by lazy {
        androidx.lifecycle.ViewModelProvider(requireActivity())[HabitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        // Nombre del usuario — ya lo tienes en el ViewModel
        binding.tvUsername.text = habitViewModel.username

        // Observar hábitos para calcular stats
        habitViewModel.habitos.observe(viewLifecycleOwner) { habits ->
            val totalHabits = habits.size
            val bestStreak = habits.maxOfOrNull { it.racha } ?: 0
            val totalXP = habits.sumOf { it.racha * 10 } // 10 XP por día de racha

            binding.tvTotalHabits.text = totalHabits.toString()
            binding.tvBestStreak.text = bestStreak.toString()

            val level = totalXP / 100
            val xpInLevel = totalXP % 100
            binding.tvLevel.text = "Nivel $level"
            binding.progressXP.progress = xpInLevel
        }

        // Cerrar sesión — ya lo tienes en el ViewModel
        binding.btnLogout.setOnClickListener {
            habitViewModel.cerrarSesion()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}