package com.example.architectureexample.model

import com.example.getdata.userService

class MainRepositoryImpl : MainRepository {
    override suspend fun loadUsers(someData: Any?) = userService(15)
}