package com.example.mvc.models

import com.example.getdata.userService

class UserRepositoryImpl : UserRepository {
    override suspend fun loadUsers(someData: Any?) = userService(15)
}