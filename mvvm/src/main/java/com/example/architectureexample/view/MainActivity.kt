package com.example.architectureexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.architectureexample.databinding.ActivityMainBinding
import com.example.architectureexample.view.adapters.UserAdapter
import com.example.architectureexample.viewModel.LoadingState
import com.example.architectureexample.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()
    private val adapter: UserAdapter get() = viewModel.adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpVariables()
        setUpObservers()
        startProcesses()
    }

    private fun startProcesses() {
        viewModel.viewModelScope.launch (Dispatchers.Default) {
            viewModel.loadUsers()
        }
    }

    private fun setUpVariables() {
        binding.userAdapter = adapter
    }

    private fun setUpObservers() {
        viewModel.getUsers().observe(this) {
            if(it == null) return@observe
            adapter.addUsers(it)
            viewModel.finishLoading()
        }
        viewModel.getLoadingState().observe(this) {
            binding.llProgressIndicator.visible = it == LoadingState.LOADING
        }
    }

    private var View.visible:Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if(value) View.VISIBLE else View.GONE
    }
}