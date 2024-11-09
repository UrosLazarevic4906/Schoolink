package com.example.schoolink.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.schoolink.data.dao.ProfessorDao
import com.example.schoolink.data.database.converters.GenderTypeConverter
import com.example.schoolink.data.entities.ProfessorEntity

@Database(entities = [ProfessorEntity::class], version = 1)
@TypeConverters(GenderTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun professorDao(): ProfessorDao
}