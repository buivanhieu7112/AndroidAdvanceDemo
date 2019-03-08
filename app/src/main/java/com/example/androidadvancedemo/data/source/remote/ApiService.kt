package com.example.androidadvancedemo.data.source.remote

import com.example.androidadvancedemo.data.source.model.BaseUser
import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(): Flowable<MutableList<User>>

    @GET("search/users")
    fun getUserBySearch(@Query("q") name: String): Flowable<BaseUser>
}
