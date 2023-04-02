package com.example.mvp.contracts

import androidx.recyclerview.widget.RecyclerView
import com.example.getdata.UserList
import com.example.mvp.model.UserRepository

sealed interface UserContract {
    interface View {
        fun showUsers(users: UserList)
        fun updateState(state: LoadingState)
    }

    interface Presenter<out T: RecyclerView.Adapter<*>> {
        suspend fun getUsers()
        val adapter: T
        val view: View
        val repository: UserRepository
    }

    enum class LoadingState {
        LOADING,
        DONE;
    }
}