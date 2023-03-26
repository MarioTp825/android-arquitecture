package com.example.architectureexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.architectureexample.BR
import com.example.architectureexample.R
import com.example.architectureexample.databinding.ActivityMainBinding
import com.example.architectureexample.view.adapters.UserAdapter
import com.example.architectureexample.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { UserAdapter() }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.setVariable(
            BR.userAdapter,
            adapter
        )
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.getUsers().observe(this) {
            if(it == null) return@observe

        }
    }


}