package com.example.architectureexample.model

import androidx.lifecycle.LiveData
import com.example.getdata.UserList

interface MainRepository {
    suspend fun loadUsers(someData: Any? = null)
    fun getUsers(): LiveData<UserList?>
}