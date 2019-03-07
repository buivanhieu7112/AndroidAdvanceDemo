package com.example.androidadvancedemo.data.source.remote

import com.example.androidadvancedemo.data.source.UserDataSource
import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.Flowable
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor() : UserDataSource.Remote {
    @Inject
    lateinit var apiService: ApiService

    override fun getUsers(): Flowable<MutableList<User>> {
        return apiService.getUsers()
    }
}
