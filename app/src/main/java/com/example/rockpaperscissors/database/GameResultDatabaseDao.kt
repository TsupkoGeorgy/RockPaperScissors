package com.example.rockpaperscissors.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameResultDatabaseDao {

    @Insert
    fun insert(result: GameResult)

    @Update
    fun update(result: GameResult)

    @Query(value = "SELECT * from game_result_history_database WHERE gameId = :key")
    fun get(key: Long): GameResult

    @Query(value = "DELETE FROM game_result_history_database")
    fun clear()

    @Query(value = "SELECT * FROM game_result_history_database ORDER BY gameId DESC")
    fun getAllGameResults(): LiveData<List<GameResult>>


}
