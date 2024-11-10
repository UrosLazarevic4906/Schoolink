package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.ProfessorDao
import com.example.schoolink.data.mappers.ProfessorMapper
import com.example.schoolink.domain.models.ProfessorModel

class ProfessorRepository(
    private val professorDao: ProfessorDao
) {
    suspend fun insertProfessor(professorModel: ProfessorModel) {
        val entity = ProfessorMapper.fromModelToEntity(professorModel)
        professorDao.insertProfessor(entity)
    }

    suspend fun getProfessorByEmail(email: String): ProfessorModel? {
        val entity = professorDao.getProfessorByEmail(email)
        return entity?.let { ProfessorMapper.fromEntityToModel(it) }
    }
}