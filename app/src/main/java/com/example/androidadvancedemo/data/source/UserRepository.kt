package com.example.androidadvancedemo.data.source

import com.example.androidadvancedemo.data.source.model.BaseUser
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.data.source.remote.UserRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class UserRepository @Inject constructor(var remote: UserRemoteDataSource) : UserDataSource.Remote {
    override fun getUsers(): Flowable<MutableList<User>> {
        return remote.getUsers()
    }

    override fun getUserBySearch(name: String): Flowable<BaseUser> {
        return remote.getUserBySearch(name)
    }
}
