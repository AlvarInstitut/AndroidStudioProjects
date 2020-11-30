package com.example.coffeeshops_fragments_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

public class FirstFragmentViewModel(val database: CoffeeShopsDao, application: Application): AndroidViewModel(application) {

    private var viewModleJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModleJob)

    private var _allCoffees = MutableLiveData<List<Coffee>>()
    val allCoffees: LiveData<List<Coffee>>
        get() = _allCoffees

    override fun onCleared() {
        super.onCleared()
        viewModleJob.cancel()
    }

    fun getAllCoffees() {
        uiScope.launch {
            _allCoffees.value = getAllCoffesFromDatabase()
        }
    }

    suspend fun getAllCoffesFromDatabase(): List<Coffee> {
        return withContext(Dispatchers.IO) {
            var coffes = database.getCoffees()
            coffes
        }
    }



}
