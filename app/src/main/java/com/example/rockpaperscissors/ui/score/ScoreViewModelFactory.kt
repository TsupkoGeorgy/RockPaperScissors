package com.example.rockpaperscissors.ui.score

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rockpaperscissors.database.GameResultDatabaseDao
import java.lang.IllegalArgumentException
import javax.sql.DataSource

class ScoreViewModelFactory(
    private val dataSource: GameResultDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}