package com.example.androidadvancedemo.utils.di.modules

import com.example.androidadvancedemo.data.source.UserRepository
import com.example.androidadvancedemo.data.source.local.UserLocalDataSource
import com.example.androidadvancedemo.data.source.local.persistence.UserDatabase
import com.example.androidadvancedemo.data.source.remote.ApiService
import com.example.androidadvancedemo.data.source.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService, userDatabase: UserDatabase): UserRepository {
        return UserRepository(UserRemoteDataSource(apiService), UserLocalDataSource(userDatabase.userDao(),userDatabase.petDao()))
    }
}
