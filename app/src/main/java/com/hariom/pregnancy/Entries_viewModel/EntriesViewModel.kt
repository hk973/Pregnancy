package com.hariom.pregnancy.Entries_viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hariom.pregnancy.Entries_model.Entries
import com.hariom.pregnancy.Entries_model.EntriesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EntriesViewModel(private val repository: EntriesRepository) : ViewModel() {

    val vitalsList: StateFlow<List<Entries>> =
        repository.getAllVitals().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
        )

    fun addVitals(
        systolic: Int,
        diastolic: Int,
        heartRate: Int,
        weight: Float,
        babyKicks: Int
    ) {
        viewModelScope.launch {
            val entry = Entries(
                systolic = systolic,
                diastolic = diastolic,
                heartRate = heartRate,
                weight = weight,
                babyKicks = babyKicks
            )
            repository.insertVitals(entry)
        }
    }

    fun deleteVitals(entryId: Int) {
        viewModelScope.launch {
            repository.deleteVitals(entryId)
        }
    }
}
