package com.example.androidadvancedemo.ui

import android.util.Log
import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidadvancedemo.R
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.utils.ItemCLickListener
import com.example.androidadvancedemo.utils.ItemMenuClickListener
import kotlinx.android.synthetic.main.adapter_user.view.*

class UserAdapter(
    private val itemCLickListener: ItemCLickListener,
    private val itemMenuClickListener: ItemMenuClickListener
) :
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
        return UserViewHolder(view, itemMenuClickListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), itemCLickListener)
    }

    class UserViewHolder(itemView: View, val itemMenuClickListener: ItemMenuClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        private lateinit var user: User

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            val save: MenuItem = menu!!.add(Menu.NONE, 1, 1, "Save")
            val cancel: MenuItem = menu!!.add(Menu.NONE, 2, 2, "Cancel")
            save.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Log.d("CONTEXT_MENU", "save")
                    itemMenuClickListener.onItemMenuClick(user)
                    return true
                }
            })
            cancel.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Log.d("CONTEXT_MENU", "cancel")
                    return true
                }
            })
        }

        fun bind(user: User, itemCLickListener: ItemCLickListener) {
            this.user = user
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
