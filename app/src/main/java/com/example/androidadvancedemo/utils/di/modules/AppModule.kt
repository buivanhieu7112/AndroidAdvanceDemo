package com.example.androidadvancedemo.utils.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.androidadvancedemo.data.source.local.persistence.UserDatabase
import com.example.androidadvancedemo.data.source.local.persistence.UserDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideUserDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(application.applicationContext, UserDatabase::class.java, DATABASE_NAME)
            .build()
    }
}
