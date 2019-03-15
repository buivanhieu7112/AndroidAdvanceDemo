package com.example.androidadvancedemo.data.source

import com.example.androidadvancedemo.data.source.model.BaseUser
import com.example.androidadvancedemo.data.source.model.Pet
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.data.source.model.UserAndAllPet
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserDataSource {

    interface Remote {
        fun getUsers(): Flowable<MutableList<User>>

        fun getUserBySearch(name: String): Flowable<BaseUser>
    }

    interface Local {
        fun insertUser(vararg user: User): Completable

        fun getUsersLocal(): Flowable<MutableList<User>>

        fun getUserLocalBySearch(name: String): Flowable<MutableList<User>>

        fun deleteUser()

        fun insertPet(vararg pet: Pet): Completable

        fun getPetByUser(userId: Int): Flowable<MutableList<Pet>>

        fun getUserAndPet(): Flowable<UserAndAllPet>
    }
}
