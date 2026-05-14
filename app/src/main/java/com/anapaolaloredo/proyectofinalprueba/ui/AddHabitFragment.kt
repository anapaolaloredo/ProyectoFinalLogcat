package com.anapaolaloredo.proyectofinalprueba.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.anapaolaloredo.proyectofinalprueba.databinding.FragmentAddHabitBinding
import com.anapaolaloredo.proyectofinalprueba.viewmodel.HabitViewModel

class AddHabitFragment : DialogFragment() {

    private var _binding: FragmentAddHabitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombreHabito.text.toString()
            val descripcion = binding.etDescripcion.text.toString()

            if (nombre.isBlank()) {
                Toast.makeText(requireContext(), "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.agregarHabito(nombre, descripcion)
            dismiss()
        }

        binding.btnCancelar.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
