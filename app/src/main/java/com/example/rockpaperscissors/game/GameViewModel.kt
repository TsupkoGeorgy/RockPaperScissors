package com.example.rockpaperscissors.game

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rockpaperscissors.database.GameResult
import javax.sql.DataSource

class GameViewModel : ViewModel() {

    private lateinit var wordList: MutableList<String>


    //Необходимо сделать дополнительнй не изменяемый лист List
    //и заполнять его из _gameResultList
    private val _tempList = MutableLiveData<List<String>>()
    val tempList: LiveData<List<String>>
        get() = _tempList


    private var _gameResultList = MutableLiveData<MutableList<String>>()
//    val gameResultList: LiveData<MutableList<String>>
//        get() = _gameResultList



    private val _gameResult = MutableLiveData<String>()
    val gameResult: LiveData<String>
        get() = _gameResult


    private val _playerChoice = MutableLiveData<String>()
    val playerChoice: LiveData<String>
        get() = _playerChoice

//    private val _gameResultList = MutableLiveData<MutableList<String>>()
//    val gameResultList: LiveData<MutableList<String>>
//        get() = _gameResultList

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
        resetList()
        _gameResultList.value = mutableListOf()
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

        _gameResultList.value?.add(_gameResult.value!!)
        _tempList.value = _gameResultList.value

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


class GameViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
