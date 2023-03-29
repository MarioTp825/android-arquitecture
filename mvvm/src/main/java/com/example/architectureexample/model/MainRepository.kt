package com.example.architectureexample.model

import com.example.getdata.UserList

interface MainRepository {
    suspend fun loadUsers(someData: Any? = null): UserList
}