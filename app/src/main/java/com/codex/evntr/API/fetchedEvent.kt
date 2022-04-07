package com.codex.evntr.API

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Event")
data class fetchedEvent(
    @PrimaryKey val id: String,
    val CreatedAt: String,
    val Rev: String,
    val Type: String,
    val UpdatedAt: String,
    val category: String,
    val description: String,
    val digitalEvent: Boolean,
    val eventImage: String,
    val host: String,
    var location: String,
    val price: String,
    val speaker: String,
    val time: String,
    val title: String,
    val result: String,
)