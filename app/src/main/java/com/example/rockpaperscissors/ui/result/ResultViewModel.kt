package com.example.rockpaperscissors.ui.result

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rockpaperscissors.database.GameResultDatabaseDao
import javax.sql.DataSource

class ResultViewModel(
    dataSource: GameResultDatabaseDao,
    id: Long
) : ViewModel() {

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String>
        get() = _resultText

    private val _scoreText = MutableLiveData<String>()
    val scoreText: LiveData<String>
        get() = _scoreText

    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    private val _eventCloseGame = MutableLiveData<Boolean>()
    val eventCloseGame: LiveData<Boolean>
        get() = _eventCloseGame

    init {
        _resultText.value = ""
        _scoreText.value = ""
    }

    fun onRestartGameComplete() {
        _eventPlayAgain.value = false
    }

    fun onRestartGame() {
        _eventPlayAgain.value = true
    }

    fun onClose() {
        _eventCloseGame.value = true
    }

    fun onCloseComplete() {
        _eventCloseGame.value = false
    }
}