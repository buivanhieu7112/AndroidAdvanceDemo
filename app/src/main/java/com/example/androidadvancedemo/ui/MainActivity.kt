package com.example.androidadvancedemo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvancedemo.R
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.utils.ItemCLickListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ItemCLickListener {
    private lateinit var viewModel: MainViewModel
    private var userAdapter = UserAdapter(this)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        initData()
    }

    private fun setupView(){
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAdapter(userAdapter)
        viewModel.getUsers()
        recyclerView.adapter = userAdapter
    }

    override fun onItemClicked(user: User) {
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
    }
}
