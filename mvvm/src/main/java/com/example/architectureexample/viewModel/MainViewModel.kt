package com.example.architectureexample.viewModel

import androidx.lifecycle.ViewModel
import com.example.architectureexample.observers.MainObserver

class MainViewModel: ViewModel() {
    private val observer = MainObserver()

    suspend fun loadUsers() = observer.loadUsers(null)

    fun getUsers() = observer.getUsers()
}