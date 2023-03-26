package com.example.architectureexample.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architectureexample.databinding.ItemRvUserBinding
import com.example.getdata.UserContainer

class UserAdapter(private val listener: (id: Int?) -> Unit): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val users = listOf<UserContainer>()
    private var index = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder (
        ItemRvUserBinding.inflate(LayoutInflater.from(parent.context), parent,false),
        listener
    )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class ViewHolder(private val view: ItemRvUserBinding, val listener: (id: Int?) -> Unit): RecyclerView.ViewHolder(view.root) {
        var currentId: Int? = null

        init {
            view.ivUserPicture.setOnClickListener {
                listener(currentId)
            }
        }

        fun bind(user: UserContainer) {
            currentId = user.id
            view
        }
    }
}