package com.example.rockpaperscissors.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rockpaperscissors.database.GameResultDatabaseDao


class GameViewModelFactory(
   /* private val dataSource: GameResultDatabaseDao,
    private val application: Application*/
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
