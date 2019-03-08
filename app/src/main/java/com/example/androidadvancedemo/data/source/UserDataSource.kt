package com.example.androidadvancedemo.data.source

import com.example.androidadvancedemo.data.source.model.BaseUser
import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.Flowable

interface UserDataSource {

    interface Remote {
        fun getUsers(): Flowable<MutableList<User>>

        fun getUserBySearch(name: String): Flowable<BaseUser>
    }
}
