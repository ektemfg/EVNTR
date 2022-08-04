package com.codex.evntr.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codex.evntr.API.Event
import com.codex.evntr.API.EventGoing

@Database(entities = [Event::class, EventGoing::class], version = 1, exportSchema = false)
@TypeConverters(com.codex.evntr.database.TypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDAO

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "Events_DB"
            ).build()
        }
    }
}
