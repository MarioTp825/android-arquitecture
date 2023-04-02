package com.example.mvp.model

import com.example.getdata.UserList

interface UserRepository {
    suspend fun loadUsers(someData: Any? = null): UserList
}