package com.example.rockpaperscissors.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameViewModel : ViewModel() {

    private lateinit var wordList: MutableList<String>

    //Необходимо сделать дополнительнй не изменяемый лист List
    //и заполнять его из _gameResultList
    private val _tempList = MutableLiveData<MutableList<String>>()
    val tempList: LiveData<MutableList<String>>
        get() = _tempList

    private val _gameResultList = MutableLiveData<List<String>>()
    val gameResultList: LiveData<List<String>>
        get() = _gameResultList

    private val _gameResult = MutableLiveData<String>()
    val gameResult: LiveData<String>
        get() = _gameResult


    private val _playerChoice = MutableLiveData<String>()

    fun onRockButtonClicked() {
        _playerChoice.value = "Rock"
        gameResult()

    }

    fun onPaperButtonClicked() {
        _playerChoice.value = "Paper"
        gameResult()
    }

    fun onScissorsButtonClicked() {
        _playerChoice.value = "Scissors"
        gameResult()
    }

    init {
        _tempList.value = mutableListOf()
        _gameResultList.value = listOf()
        _gameResult.value = "Make your choice"
    }


    private fun gameResult() {
        resetList()
        if (_playerChoice.value == wordList.first()) _gameResult.value = "Tie!"
        else if (
            (_playerChoice.value == "Rock" && wordList.first() == "Scissors") ||
            (_playerChoice.value == "Paper" && wordList.first() == "Rock") ||
            (_playerChoice.value == "Scissors" && wordList.first() == "Paper")
        )
            _gameResult.value = "You win!"
        else
            _gameResult.value = "You lose!"

        _tempList.value?.add(_gameResult.value!!)
        _gameResultList.value = _tempList.value

        Log.i("adapter", "temp data = ${_tempList.value}")

    }

    private fun resetList() {
        wordList = mutableListOf(
            "Rock",
            "Paper",
            "Scissors"
        )
        wordList.shuffle()
    }
}

