package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolink.data.entities.ProfessorEntity

@Dao
interface ProfessorDao{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProfessor(professor: ProfessorEntity)

    @Query("SELECT * FROM professors WHERE email = :email")
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?
}