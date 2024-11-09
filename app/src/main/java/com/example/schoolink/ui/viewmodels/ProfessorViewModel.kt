package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.domain.repository.ProfessorRepository
import kotlinx.coroutines.launch

class ProfessorViewModel(
    private val repository: ProfessorRepository
) : ViewModel() {
    fun addProfessor(professorModel: ProfessorModel) {
        viewModelScope.launch {
            repository.insertProfessor(professorModel)
        }
    }
}