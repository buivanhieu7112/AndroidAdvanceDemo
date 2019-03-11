package com.example.androidadvancedemo.data.source.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidadvancedemo.data.source.local.persistence.UserDatabase.Companion.DATABASE_VERSION
import com.example.androidadvancedemo.data.source.model.User

@Database(entities = [User::class], version = DATABASE_VERSION, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Room-database"
    }
}
