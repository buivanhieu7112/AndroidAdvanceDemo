package com.example.androidadvancedemo.ui

import android.util.Log
import com.example.androidadvancedemo.base.BaseViewModel
import com.example.androidadvancedemo.data.source.UserRepository
import com.example.androidadvancedemo.data.source.model.BaseUser
import com.example.androidadvancedemo.data.source.model.User
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    private lateinit var userAdapter: UserAdapter

    fun getUsers() {
        val disposable: Disposable = userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // moi lan insert la vo day
                    result ->
                getUsersSuccess(result)
            }, { error -> getUsersFailure(error) })
        launchDisposable(disposable)
//        arrayListOf<Int>(1,2,3,4)
//        "1,2,3,4".split(",")
    }

    fun getUserBySearch(name: String) {
        val disposable: Disposable = userRepository.getUserBySearch(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> getUserBySearchSuccess(result) }, { error -> getUsersFailure(error) })
        launchDisposable(disposable)
    }

    fun getAdapter(userAdapter: UserAdapter) {
        this.userAdapter = userAdapter
    }

    private fun getUsersSuccess(result: MutableList<User>) {
        userAdapter.submitList(result)
    }

    private fun getUsersFailure(error: Throwable) {
        Log.e("ERROR", error.localizedMessage.toString())
    }

    private fun getUserBySearchSuccess(result: BaseUser) {
        userAdapter.submitList(result.items)
    }

    fun insertUserToLocal(vararg user: User) {
        userRepository.insertUser(*user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    Log.d("SUCCESS", "INSERT SUCCESSFUL")
                }

                override fun onSubscribe(d: Disposable) {
                    launchDisposable(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("ERROR", e.localizedMessage)
                }
            })
    }

    fun getUsersLocal() {
        val disposable = userRepository.getUsersLocal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> userAdapter.submitList(result) }, { error -> error.localizedMessage })
        launchDisposable(disposable)
    }

    fun getUsersLocalBySearch(name: String) {
        val disposable = userRepository.getUserLocalBySearch(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> userAdapter.submitList(result) }, { error -> error.localizedMessage })
        launchDisposable(disposable)
    }

    fun deleteAllUser() {
        userRepository.deleteUser()
    }
}
