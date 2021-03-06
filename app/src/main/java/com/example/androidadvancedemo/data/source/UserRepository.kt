package com.example.androidadvancedemo.data.source

import com.example.androidadvancedemo.data.source.model.BaseUser
import com.example.androidadvancedemo.data.source.model.Pet
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.data.source.model.UserAndAllPet
import io.reactivex.Completable
import io.reactivex.Flowable

class UserRepository(
    private val remote: UserDataSource.Remote,
    private val local: UserDataSource.Local
) : UserDataSource.Remote, UserDataSource.Local {
    override fun insertPet(vararg pet: Pet): Completable {
        return local.insertPet(*pet)
    }

    override fun getPetByUser(userId: Int): Flowable<MutableList<Pet>> {
        return local.getPetByUser(userId)
    }

    override fun getUserAndPet(): Flowable<UserAndAllPet> {
        return local.getUserAndPet()
    }

    override fun getUsers(): Flowable<MutableList<User>> {
        return remote.getUsers()
    }

    override fun getUserBySearch(name: String): Flowable<BaseUser> {
        return remote.getUserBySearch(name)
    }

    override fun insertUser(vararg user: User): Completable {
        return local.insertUser(*user)
    }

    override fun getUsersLocal(): Flowable<MutableList<User>> {
        return local.getUsersLocal()
    }

    override fun getUserLocalBySearch(name: String): Flowable<MutableList<User>> {
        return local.getUserLocalBySearch(name)
    }

    override fun deleteUser() {
        local.deleteUser()
    }
}
