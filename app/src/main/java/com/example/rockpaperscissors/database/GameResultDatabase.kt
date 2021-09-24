package com.example.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameResult::class], version = 1, exportSchema = false)
abstract class GameResultDatabase: RoomDatabase() {

    abstract  val gameResultDatabaseDao: GameResultDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE: GameResultDatabase? = null

        fun getInstance(context: Context) :GameResultDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                   instance = Room.databaseBuilder(
                       context.applicationContext,
                       GameResultDatabase::class.java,
                       "game_result_history_database"
                   )
                       .fallbackToDestructiveMigration()
                       .build()
                }

                return instance
            }
        }
    }
}