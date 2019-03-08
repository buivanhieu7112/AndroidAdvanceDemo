package com.example.androidadvancedemo.data.source.model

import com.google.gson.annotations.SerializedName

data class BaseUser(@SerializedName("items") var items: MutableList<User>?)
