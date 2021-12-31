package com.example.rockpaperscissors.ui.score

import android.app.Application
import androidx.lifecycle.*
import com.example.rockpaperscissors.database.GameResultDatabaseDao
import kotlinx.coroutines.*

class ScoreViewModel(
    val dataSource: GameResultDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val games = dataSource.getAllGameResults()

    val clearButtonVisible = Transformations.map(games) {
        it?.isNotEmpty()
    }



    private val _eventBack = MutableLiveData<Boolean>()
    val eventBack: LiveData<Boolean>
        get() = _eventBack


    fun onClear() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataSource.clear()
        }
    }

    fun onBack() {
        _eventBack.value = true
    }

    fun onBackComplete() {
        _eventBack.value = false
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}