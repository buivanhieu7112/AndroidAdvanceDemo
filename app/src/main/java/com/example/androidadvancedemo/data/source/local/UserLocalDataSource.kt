package com.example.androidadvancedemo.data.source.local

import com.example.androidadvancedemo.data.source.UserDataSource
import com.example.androidadvancedemo.data.source.local.persistence.PetDao
import com.example.androidadvancedemo.data.source.local.persistence.UserDao
import com.example.androidadvancedemo.data.source.model.Pet
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.data.source.model.UserAndAllPet
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao, private val petDao: PetDao) :
    UserDataSource.Local {
    override fun insertPet(vararg pet: Pet): Completable {
        return petDao.insertPet(*pet)
    }

    override fun getPetByUser(userId: Int): Flowable<MutableList<Pet>> {
        return petDao.getPetsForUser(userId)
    }

    override fun getUserAndPet(): Flowable<UserAndAllPet> {
        return petDao.getUserAndPets()
    }

    override fun insertUser(vararg user: User): Completable {
        return userDao.insertUser(*user)
    }

    override fun getUsersLocal(): Flowable<MutableList<User>> {
        return userDao.getUsers()
    }

    override fun getUserLocalBySearch(name: String): Flowable<MutableList<User>> {
        return userDao.getUserBySearch(name)
    }

    override fun deleteUser() {
        userDao.deleteAllUser()
    }
}
