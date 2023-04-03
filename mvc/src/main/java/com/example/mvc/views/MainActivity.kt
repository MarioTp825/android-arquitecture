package com.example.mvc.views

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getdata.UserContainer
import com.example.getdata.UserList
import com.example.mvc.controllers.UserController
import com.example.mvc.databinding.ActivityMainBinding
import com.example.mvc.databinding.FragmentUserDialogBinding
import com.example.mvc.views.adapters.UserAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity(), UserView {
    private val dialogBinding: FragmentUserDialogBinding by lazy {
        FragmentUserDialogBinding.inflate(layoutInflater)
    }

    private val dialog by lazy {
        buildDialog(this, dialogBinding.root)
    }

    private val controller by lazy {
        UserController(this)
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter = UserAdapter { user ->
        user ?: return@UserAdapter
        showUserImage(user)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpViews()
        loadUsers()
    }

    private fun loadUsers() {
        controller.loadUsers()
    }

    private fun setUpViews() {
        binding.rvMainContainer.apply {
            layoutManager = buildLinearLayout()
            adapter = this@MainActivity.adapter
        }
        dialogBinding.btnDismiss.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun buildLinearLayout() =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    private fun showUserImage(user: UserContainer) {
        bindData(user)
        dialog.show()
    }

    private fun bindData(user: UserContainer) {
        dialogBinding.let {
            it.tvName.text = user.fullName
            Picasso.get()
                .load(user.url)
                .resize(150, 150)
                .centerCrop()
                .into(it.ivUserPicture)
        }
    }

    private fun buildDialog(context: Context, view: View?) = AlertDialog.Builder(context).let {
        it.setCancelable(false)
        it.setView(view)
        it.create()
    }

    override fun showUsers(users: UserList) {
        runOnUiThread {
            adapter.addUsers(users)
        }
    }

    override fun updateLoading(state: LoadingState) {
        runOnUiThread {
            binding.llProgressIndicator.visible = state == LoadingState.LOADING
        }
    }

    override val scope: CoroutineScope = lifecycleScope

    private var View.visible: Boolean
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
        }
}