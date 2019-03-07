package com.example.androidadvancedemo.data.source.remote

import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Flowable<MutableList<User>>
}
