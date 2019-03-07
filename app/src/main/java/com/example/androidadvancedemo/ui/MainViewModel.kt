package com.example.androidadvancedemo.ui

import android.util.Log
import com.example.androidadvancedemo.base.BaseViewModel
import com.example.androidadvancedemo.data.source.UserRepository
import com.example.androidadvancedemo.data.source.model.User
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
            .subscribe({ result -> handleSuccess(result) }, { error -> handleFailure(error) })
        launchDiposable(disposable)
    }

    fun getAdapter(userAdapter: UserAdapter) {
        this.userAdapter = userAdapter
    }

    private fun handleSuccess(result: MutableList<User>) {
        userAdapter.submitList(result)
    }

    private fun handleFailure(error: Throwable) {
        Log.e("ERROR", error.localizedMessage)
    }
}
