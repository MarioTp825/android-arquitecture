package com.example.architectureexample.model

import com.example.getdata.UserList
import com.example.getdata.usersContainers
import kotlinx.coroutines.delay

class MainRepositoryImpl: MainRepository {
    override suspend fun loadUsers(someData: Any?):UserList {
        delay(2000)
        return usersContainers
    }
}