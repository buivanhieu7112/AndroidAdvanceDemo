package com.example.androidadvancedemo.data.source.local

import com.example.androidadvancedemo.data.source.UserDataSource
import com.example.androidadvancedemo.data.source.local.persistence.UserDao
import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao) : UserDataSource.Local {
    override fun insertUser(vararg user: User): Completable {
        return userDao.insertUser(*user)
    }

    override fun getUsersLocal(): Flowable<MutableList<User>> {
        return userDao.getUsers()
    }

    override fun getUserLocalBySearch(name: String): Flowable<MutableList<User>> {
        return userDao.getUserBySearch(name)
    }

    override fun deleteUser(){
        userDao.deleteAllUser()
    }
}
