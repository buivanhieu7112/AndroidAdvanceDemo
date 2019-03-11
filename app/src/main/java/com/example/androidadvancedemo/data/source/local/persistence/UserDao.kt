package com.example.androidadvancedemo.data.source.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User): Completable

    @Query("SELECT * FROM USER")
    fun getUsers(): Flowable<MutableList<User>>

    @Query("SELECT * FROM USER WHERE name LIKE :name")
    fun getUserBySearch(name: String): Flowable<MutableList<User>>

    @Query("DELETE FROM USER")
    fun deleteAllUser()
}
