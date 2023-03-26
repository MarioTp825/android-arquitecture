package com.example.architectureexample.observers

import androidx.databinding.BaseObservable
import com.example.architectureexample.model.MainRepository
import com.example.architectureexample.model.MainRepositoryImpl

class MainObserver: BaseObservable() {
    private val repository: MainRepository = MainRepositoryImpl()

    fun getUsers() = repository.getUsers()

    suspend fun loadUsers(someData: Any?) {
        repository.loadUsers(null)
    }
}