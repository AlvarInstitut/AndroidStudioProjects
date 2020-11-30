package com.example.coffeeshops_fragments_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class SecondFragmentViewModel(val database: CoffeeShopsDao, application: Application): AndroidViewModel(application) {

    private var viewModleJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModleJob)

    private var _coffeWitlAllComments = MutableLiveData<List<CoffeeWithComments>>()
    val coffeWitlAllComments: LiveData<List<CoffeeWithComments>>
        get() = _coffeWitlAllComments

    private var _coffeeName = MutableLiveData<String?>()
    val coffeeName: LiveData<String?>
        get() = _coffeeName

    override fun onCleared() {
        super.onCleared()
        viewModleJob.cancel()
    }

    fun getAllComments(key: Int) {
        uiScope.launch {
            _coffeWitlAllComments.value = getAllCommentsFromDatabase(key)
        }
    }

    suspend fun getAllCommentsFromDatabase(key: Int): List<CoffeeWithComments> {
        return withContext(Dispatchers.IO) {
            var comments = database.getCommentsFrom(key)
            withContext(Dispatchers.Main) {
                _coffeeName.value = comments.first().coffee.title
            }
            comments
        }
    }
}