package com.anapaolaloredo.proyectofinalprueba.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anapaolaloredo.proyectofinalprueba.data.dao.HabitDao
import com.anapaolaloredo.proyectofinalprueba.data.dao.UserDao
import com.anapaolaloredo.proyectofinalprueba.data.entity.Habit
import com.anapaolaloredo.proyectofinalprueba.data.entity.User

@Database(entities = [User::class, Habit::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "skilltree_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
