package com.example.mvc.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.getdata.UserContainer
import com.example.getdata.UserList
import com.example.mvc.databinding.ItemRvUserBinding

typealias UserListener = (UserContainer?) -> Unit

class UserAdapter(private val listener: UserListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val users = mutableListOf<UserContainer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemRvUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        listener
    )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addUsers(newUsers: UserList) {
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val view: ItemRvUserBinding,
        private val listener: UserListener
    ) : RecyclerView.ViewHolder(view.root) {
        private var currentUser: UserContainer? = null

        init {
            view.cvUserItem.setOnClickListener {
                listener(currentUser)
            }
        }

        fun bind(user: UserContainer) {
            currentUser = user
            view.apply {
                tvName.text = user.fullName
                tvId.text = user.id.toString()
                tvUsername.text = user.username
            }
        }

    }
}