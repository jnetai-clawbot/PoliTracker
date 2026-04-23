package com.jnetai.politracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jnetai.politracker.model.*

@Database(entities = [Bill::class, Representative::class, Action::class], version = 1, exportSchema = false)
abstract class PoliTrackerDatabase : RoomDatabase() {
    abstract fun dao(): PoliTrackerDao
    companion object {
        @Volatile private var INSTANCE: PoliTrackerDatabase? = null
        fun getInstance(context: Context): PoliTrackerDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context, PoliTrackerDatabase::class.java, "politracker.db")
                .fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}