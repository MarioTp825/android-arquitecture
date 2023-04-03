package com.example.mvc.models

import com.example.getdata.UserList

interface UserRepository {
    suspend fun loadUsers(someData: Any? = null): UserList
}