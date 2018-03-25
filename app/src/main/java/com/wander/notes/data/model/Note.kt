package com.wander.notes.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Note(
        var noteTitle: String = "",
        var noteText: String = "",
        @PrimaryKey
        var createTimeStamp: String = "",
        var lastEditedTimestamp: String = "")

