package com.example.androidadvancedemo.data.source.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

data class User @Inject constructor(
    @SerializedName("id") var id: String?,
    @SerializedName("login") var name: String?,
    @SerializedName("avatar_url") var avatar: String?
)
