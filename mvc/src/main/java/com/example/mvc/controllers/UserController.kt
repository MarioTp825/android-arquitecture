package com.example.mvc.controllers

import com.example.mvc.models.UserRepository
import com.example.mvc.models.UserRepositoryImpl
import com.example.mvc.views.LoadingState
import com.example.mvc.views.UserView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserController(private val view: UserView) {
    private val repository: UserRepository = UserRepositoryImpl()

    fun loadUsers() {
        view.scope.launch (Dispatchers.IO) {
            view.updateLoading(LoadingState.LOADING)
            view.showUsers(
                repository.loadUsers(null)
            )
            view.updateLoading(LoadingState.DONE)
        }
    }
}