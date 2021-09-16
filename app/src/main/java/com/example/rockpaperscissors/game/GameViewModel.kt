package com.example.rockpaperscissors.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private lateinit var wordList: MutableList<String>


    private val _gameResult = MutableLiveData<String>()
    val gameResult: LiveData<String>
        get() = _gameResult

    //var gameResult: String = R.string.game_result.toString()


    private val _playerChoice = MutableLiveData<String>()
    val playerChoice: LiveData<String>
        get() = _playerChoice


    fun onRockButtonClicked() {
        _playerChoice.value = "Rock"
        gameResult()
        Log.i("gameResult", "game result = ${_gameResult.value}, game choise = ${wordList.first()} player choise = ${playerChoice.value}")
    }

    fun onPaperButtonClicked() {
        _playerChoice.value = "Paper"
        gameResult()
        Log.i("gameResult", "game result = ${_gameResult.value}, game choise = ${wordList.first()} player choise = ${playerChoice.value}")
    }

    fun onScissorsButtonClicked() {
        _playerChoice.value = "Scissors"
        gameResult()
        Log.i("gameResult", "game result = ${_gameResult.value}, game choice = ${wordList.first()} player choise = ${playerChoice.value}")
    }

    init {
        resetList()

        _gameResult.value = "Make your choice"
        Log.i("gameResult", "game result = ${_gameResult.value}, game choice = ${wordList.first()} player choise = ${playerChoice.value}")


    }


    fun gameResult() {
        if (_playerChoice.value == wordList.first()) _gameResult.value = "Tie!"
        else if (
            (_playerChoice.value == "Rock" && wordList.first() == "Scissors") ||
            (_playerChoice.value == "Paper" && wordList.first() == "Rock") ||
            (_playerChoice.value == "Scissors" && wordList.first() == "Paper")
        )
            _gameResult.value = "You win!"
        else
            _gameResult.value = "You lose!"
    }

    private fun resetList() {
        wordList = mutableListOf(
            "Rock",
            "Paper",
            "Scissors"
        )
        wordList.shuffle()
    }

    override fun onCleared() {
        super.onCleared()
    }
}