package com.example.architectureexample.model

import androidx.lifecycle.MutableLiveData
import com.example.getdata.UserList
import com.example.getdata.usersContainers
import kotlinx.coroutines.delay

class MainRepositoryImpl: MainRepository {
    private val usersList: MutableLiveData<UserList?> = MutableLiveData(null)

    override suspend fun loadUsers(someData: Any?) {
        delay(2000)
        usersList.value = usersContainers
    }

    override fun getUsers() = usersList
}