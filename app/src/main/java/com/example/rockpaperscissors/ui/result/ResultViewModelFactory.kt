package com.example.rockpaperscissors.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rockpaperscissors.database.GameResultDatabaseDao
import java.lang.IllegalArgumentException

class ResultViewModelFactory(
    private val dataSource: GameResultDatabaseDao,
    private val id: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(dataSource, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}