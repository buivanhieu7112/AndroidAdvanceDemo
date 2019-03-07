package com.example.androidadvancedemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidadvancedemo.R
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.utils.ItemCLickListener
import kotlinx.android.synthetic.main.adapter_user.view.*

class UserAdapter(private val itemCLickListener: ItemCLickListener) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserAdapter.UserDiffCallback()) {

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), itemCLickListener)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User, itemCLickListener: ItemCLickListener) {
            itemView.textViewUserName.text = user.name
            Glide.with(itemView.imageViewAvatar.context).load(user.avatar)
                .apply(RequestOptions().circleCrop().placeholder(R.drawable.ic_launcher_foreground))
                .into(itemView.imageViewAvatar)

            itemView.setOnClickListener {
                itemCLickListener.onItemClicked(user)
            }
        }
    }
}
