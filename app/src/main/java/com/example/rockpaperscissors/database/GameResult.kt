package com.example.rockpaperscissors.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game_result_history_database")
data class GameResult(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "game_result")
    var result: String = "",

    @ColumnInfo(name = "game_score")
    var score: String = ""
)
