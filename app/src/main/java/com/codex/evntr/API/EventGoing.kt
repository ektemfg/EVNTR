package com.codex.evntr.API

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "eventGoing")
data class EventGoing (
    @PrimaryKey var _id: String,
    var going: Boolean
)