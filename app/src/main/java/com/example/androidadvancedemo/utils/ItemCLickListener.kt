package com.example.androidadvancedemo.utils

import com.example.androidadvancedemo.data.source.model.User

interface ItemCLickListener {
    fun onItemClicked(user: User)
}
