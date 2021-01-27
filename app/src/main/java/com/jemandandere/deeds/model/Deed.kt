package com.jemandandere.deeds.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "deeds")
data class Deed(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var text: String?,
    @ColumnInfo val timestamp: Long,
    @ColumnInfo var done: Boolean
) : Serializable