package com.hariom.pregnancy.Entries_model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Entries_Dao {

    @Insert
    suspend fun insertVitals(vitals: Entries)

    @Query("SELECT * FROM entries ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<Entries>>
}