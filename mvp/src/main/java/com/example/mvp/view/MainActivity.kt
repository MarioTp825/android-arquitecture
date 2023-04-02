package com.example.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getdata.UserList
import com.example.mvp.contracts.UserContract
import com.example.mvp.contracts.UserContract.LoadingState
import com.example.mvp.databinding.ActivityMainBinding
import com.example.mvp.model.UserRepository
import com.example.mvp.model.UserRepositoryImpl
import com.example.mvp.presenter.UserPresenterImpl
import com.example.mvp.view.adapters.UserAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), UserContract.View {
    private val repository: UserRepository = UserRepositoryImpl()
    private val presenter: UserContract.Presenter<UserAdapter> by lazy {
        UserPresenterImpl(this, repository)
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpViews()
        startRequest()
    }

    private fun startRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            presenter.getUsers()
        }
    }

    private fun setUpViews() {
        binding.rvMainContainer.apply {
            layoutManager =
                buildLinearLayout()
            adapter = presenter.adapter
        }
    }

    private fun buildLinearLayout() =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun updateState(state: LoadingState) {
        runOnUiThread {
            binding.llProgressIndicator.visible = state == LoadingState.LOADING
        }
    }

    override fun showUsers(users: UserList) {
        runOnUiThread {
            presenter.adapter.addUsers(users)
        }
    }

    private var View.visible: Boolean
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
        }
}