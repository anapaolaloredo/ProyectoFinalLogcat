package com.anapaolaloredo.proyectofinalprueba.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anapaolaloredo.proyectofinalprueba.data.entity.Habit
import com.anapaolaloredo.proyectofinalprueba.databinding.ItemHabitBinding

class HabitAdapter(
    private val onComplete: (Habit) -> Unit,
    private val onDelete: (Habit) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(DiffCallback()) {

    inner class HabitViewHolder(private val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(habit: Habit) {
            binding.tvNombre.text = habit.nombre
            binding.tvDescripcion.text = habit.descripcion
            binding.tvRacha.text = "🔥 ${habit.racha} días"
            binding.tvXp.text = "⚡ ${habit.xp} XP"
            binding.btnCompletar.isEnabled = !habit.completadoHoy
            binding.btnCompletar.alpha = if (habit.completadoHoy) 0.4f else 1.0f
            binding.btnCompletar.setOnClickListener { onComplete(habit) }
            binding.btnEliminar.setOnClickListener { onDelete(habit) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Habit>() {
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Habit, newItem: Habit) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
