package com.example.androidadvancedemo.data.source.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.androidadvancedemo.data.source.model.Pet
import com.example.androidadvancedemo.data.source.model.UserAndAllPet
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PetDao {
    @Insert
    fun insertPet(vararg pet: Pet): Completable

    @Query("SELECT * FROM Pet WHERE user_id = :userId")
    fun getPetsForUser(userId: Int): Flowable<MutableList<Pet>>

    @Transaction
    @Query("SELECT * FROM USER")
    fun getUserAndPets(): Flowable<UserAndAllPet>
}
