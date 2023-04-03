package com.example.mvc.views

import com.example.getdata.UserList
import kotlinx.coroutines.CoroutineScope

interface UserView {
    fun showUsers(users: UserList)
    fun updateLoading(state: LoadingState)

    val scope: CoroutineScope
}

enum class LoadingState {
    LOADING,
    DONE;
}