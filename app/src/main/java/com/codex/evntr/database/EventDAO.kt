package com.codex.evntr.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codex.evntr.API.Event
import com.codex.evntr.API.EventGoing

@Dao
interface EventDAO {
    @Query("SELECT * FROM eachEvent LIMIT 25")
    fun getEvents(): List<Event>

    @Query("SELECT * FROM eachEvent")
    suspend fun dbSize(): List<Event>

    @Query("SELECT * FROM eachEvent WHERE _id = :eventId LIMIT 1")
    fun getEvent(eventId: String): Event

    @Query("SELECT * FROM eventGoing")
    fun getGoingEvents(): List<EventGoing>

    @Query("SELECT * FROM eachEvent WHERE _id = :eventId LIMIT 1")
    fun getLiveEvent(eventId: String): LiveData<Event>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEvent(event: Event)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun going(event: EventGoing)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvents(event: Event)

    @Query("DELETE FROM eachEvent")
    fun deleteAllEvents()

    @Delete
    fun deleteEvent(event: Event)
}