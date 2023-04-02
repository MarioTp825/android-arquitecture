package com.example.mvp.presenter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.getdata.UserContainer
import com.example.mvp.contracts.UserContract
import com.example.mvp.databinding.FragmentUserDialogBinding
import com.example.mvp.model.UserRepository
import com.example.mvp.view.adapters.UserAdapter
import com.squareup.picasso.Picasso

class UserPresenterImpl(
    override val view: UserContract.View,
    override val repository: UserRepository,
) : UserContract.Presenter<UserAdapter> {
    private var dialogBinding: FragmentUserDialogBinding? = null

    override suspend fun getUsers() {
        view.updateState(UserContract.LoadingState.LOADING)
        view.showUsers(
            repository.loadUsers(null)
        )
        view.updateState(UserContract.LoadingState.DONE)
    }

    override val adapter by lazy {
        UserAdapter { user, context ->
            user ?: return@UserAdapter
            showUserImage(user, context)
        }
    }

    private fun showUserImage(user: UserContainer, context: Context) {
        dialogBinding = inflateBinding(context)
        val dialog = buildDialog(context, dialogBinding?.root)

        dialogBinding?.btnDismiss?.setOnClickListener {
            dialog.dismiss()
        }

        bindData(user)
        dialog.show()
    }

    private fun inflateBinding(context: Context) =
        FragmentUserDialogBinding.inflate(LayoutInflater.from(context))

    private fun bindData(user: UserContainer) {
        dialogBinding?.let {
            it.tvName.text = user.fullName
            Picasso.get()
                .load(user.url)
                .resize(150,150)
                .centerCrop()
                .into(it.ivUserPicture)
        }
    }

    private fun buildDialog(context: Context, view: View?) = AlertDialog.Builder(context).let {
        it.setCancelable(false)
        it.setView(view)
        it.setOnDismissListener { dialogBinding = null }
        it.create()
    }
}