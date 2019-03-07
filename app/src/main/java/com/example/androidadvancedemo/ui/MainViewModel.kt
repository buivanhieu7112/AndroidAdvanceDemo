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
    fun getUsers() {
        val disposable: Disposable = userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> handleSuccess(result)}, { error -> handleFailure(error) })
        launchDiposable(disposable)
    }

    private fun handleSuccess(result: MutableList<User>) {
        Log.d("SUCCESS", "${result.size} ${result[1].name}")
    }

    private fun handleFailure(error: Throwable) {
        Log.e("ERROR", "Handle Error ${error.localizedMessage}")
    }
}
