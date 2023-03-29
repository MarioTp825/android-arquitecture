package com.example.architectureexample.viewModel

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architectureexample.databinding.FragmentUserDialogBinding
import com.example.architectureexample.observers.MainObserver
import com.example.architectureexample.view.adapters.UserAdapter
import com.example.getdata.UserContainer
import com.example.getdata.UserList
import com.squareup.picasso.Picasso

class MainViewModel : ViewModel() {
    private val usersList: MutableLiveData<UserList?> = MutableLiveData(null)
    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData(LoadingState.LOADING)
    private var dialogBinding: FragmentUserDialogBinding? = null

    val adapter by lazy {
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

    private val observer = MainObserver()

    suspend fun loadUsers() {
        usersList.postValue(
            observer.loadUsers()
        )
    }

    fun getUsers() = usersList

    fun getLoadingState() = loadingState

    fun finishLoading() {
        loadingState.value = LoadingState.DONE
    }

}

enum class LoadingState {
    LOADING,
    DONE;
}