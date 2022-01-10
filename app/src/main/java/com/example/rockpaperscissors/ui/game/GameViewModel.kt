package com.example.rockpaperscissors.game

import android.app.Application
import androidx.lifecycle.*
import com.example.rockpaperscissors.database.GameResult
import com.example.rockpaperscissors.database.GameResultDatabaseDao
import kotlinx.coroutines.*

class GameViewModel(
    private val dataSource: GameResultDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private lateinit var wordList: MutableList<String>

    private val _gameResult = MutableLiveData<String>()
    val gameResult: LiveData<String>
        get() = _gameResult

    // Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    private var _score = MutableLiveData<String>()
    private val _playerChoice = MutableLiveData<String>()


    private var gameCount: Int = 0
    private var playerScore = 0
    private var gameScore = 0


    fun onRockButtonClicked() {
        _playerChoice.value = "Rock"
        uiScope.launch {
            gameResult()
        }
    }

    fun onPaperButtonClicked() {
        _playerChoice.value = "Paper"
        uiScope.launch {
            gameResult()
        }
    }

    fun onScissorsButtonClicked() {
        _playerChoice.value = "Scissors"
        uiScope.launch {
            gameResult()
        }

    }

    init {
        _gameResult.value = "Make your choice"

    }

    private suspend fun gameResult() {
        resetList()
        play()
        gameCount++
        if (gameCount == 5) {
            setResultScore()
            val gameResult = GameResult(0, _gameResult.value!!, _score.value!!)

            uiScope.launch {
                insert(gameResult)
            }
        }
        gamePlayed(gameCount)

    }

    private fun play() {
        if (_playerChoice.value == wordList.first()) {
            playerScore += 0
            gameScore += 0
            _gameResult.value = "Tie!"
            _score.value = "$playerScore || $gameScore "
        } else if (
            (_playerChoice.value == "Rock" && wordList.first() == "Scissors") ||
            (_playerChoice.value == "Paper" && wordList.first() == "Rock") ||
            (_playerChoice.value == "Scissors" && wordList.first() == "Paper")
        ) {
            playerScore += 1
            gameScore += 0
            _gameResult.value = "You win!"
            _score.value = "$playerScore || $gameScore "
        } else {
            playerScore += 0
            gameScore += 1
            _gameResult.value = "You lose!"
            _score.value = "$playerScore || $gameScore "
        }
    }

    private fun setResultScore() {
        when {
            playerScore == gameScore -> _gameResult.value = "Tie!"
            playerScore > gameScore -> _gameResult.value = "You win!"
            playerScore < gameScore -> _gameResult.value = "You lose!"
        }
    }

    private fun gamePlayed(gameCount: Int) {
        if (gameCount == 5) {
            _eventGameFinish.value = true
        }
    }


    private suspend fun insert(gameResult: GameResult) {
        withContext(Dispatchers.IO) {
            dataSource.insert(gameResult)
        }
    }

    private fun resetList() {
        wordList = mutableListOf(
            "Rock",
            "Paper",
            "Scissors"
        )
        wordList.shuffle()
    }


    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

