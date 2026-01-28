package com.hariom.pregnancy.Entries_model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "entries")
data class Entries(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val systolic: Int,
    val diastolic: Int,
    val heartRate: Int,
    val weight: Float,
    val babyKicks: Int,
    val timestamp: Long = System.currentTimeMillis()
)
