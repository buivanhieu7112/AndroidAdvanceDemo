package com.example.androidadvancedemo.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import javax.inject.Inject

@Entity(tableName = "USER", indices = [Index("id",unique = true)])
data class User @Inject constructor(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id") var id: String,
    @ColumnInfo(name = "name")
    @SerializedName("login") var name: String,
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar_url") var avatar: String
)
