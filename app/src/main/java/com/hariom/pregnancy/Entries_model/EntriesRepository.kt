package com.hariom.pregnancy.Entries_model

import kotlinx.coroutines.flow.Flow


class EntriesRepository(private val dao: Entries_Dao) {

    fun getAllVitals(): Flow<List<Entries>> {
        return dao.getAllVitals()
    }

    suspend fun insertVitals(entries: Entries) {
        dao.insertVitals(entries)
    }

    suspend fun deleteVitals(entryId: Int) {
        dao.deleteVitals(entryId)
    }
}
