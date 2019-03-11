package com.example.androidadvancedemo.data.source.remote

import com.example.androidadvancedemo.data.source.UserDataSource
import com.example.androidadvancedemo.data.source.model.BaseUser
import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.Flowable
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val apiService: ApiService) : UserDataSource.Remote {
    override fun getUsers(): Flowable<MutableList<User>> {
        return apiService.getUsers()
    }

    override fun getUserBySearch(name: String): Flowable<BaseUser> {
        return apiService.getUserBySearch(name)
    }
}
