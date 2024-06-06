package com.ubaya.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(


    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "note")
    var note:String,
    @ColumnInfo(name = "priority")
    var priority:Int,

    // MODIFIED 06/06/2024: Added is_done column to the model
    @ColumnInfo(name = "is_done")
    var is_done:Int = 0,

    ) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}