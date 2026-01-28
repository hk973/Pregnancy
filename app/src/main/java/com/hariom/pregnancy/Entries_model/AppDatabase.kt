package com.hariom.pregnancy.Entries_model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entries::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entriesDao(): Entries_Dao
}
