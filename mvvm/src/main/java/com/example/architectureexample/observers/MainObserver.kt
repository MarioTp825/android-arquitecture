package com.example.architectureexample.observers

import androidx.databinding.BaseObservable
import com.example.architectureexample.model.MainRepository
import com.example.architectureexample.model.MainRepositoryImpl
import com.example.getdata.UserList

class MainObserver: BaseObservable() {
    private val repository: MainRepository = MainRepositoryImpl()

    suspend fun loadUsers(): UserList = repository.loadUsers(null)
}