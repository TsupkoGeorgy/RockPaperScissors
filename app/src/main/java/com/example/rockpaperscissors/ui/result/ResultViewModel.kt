package com.example.rockpaperscissors.ui.result

import android.util.Log
import androidx.lifecycle.*
import com.example.rockpaperscissors.database.GameResult
import com.example.rockpaperscissors.database.GameResultDatabaseDao
import kotlinx.coroutines.*
import javax.sql.DataSource

class ResultViewModel(
    dataSource: GameResultDatabaseDao
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val database = dataSource
    private var game : GameResult = GameResult()

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
        getResult()
    }

    private fun getResult() {
        uiScope.launch {
            get()
            _resultText.value = game.result
            _scoreText.value = game.score
            Log.i("id", "game result ${game.result} game score ${game.score}")
        }
    }

    private suspend fun get() {
        withContext(Dispatchers.IO) {
            game = database.getGame()
        }
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}