package com.example.androidadvancedemo.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvancedemo.R
import com.example.androidadvancedemo.data.source.model.User
import com.example.androidadvancedemo.utils.ItemCLickListener
import com.example.androidadvancedemo.utils.ItemMenuClickListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ItemCLickListener, ItemMenuClickListener {
    private lateinit var viewModel: MainViewModel
    private var searchView: SearchView? = null
    private var userAdapter = UserAdapter(this, this)
    private var isOnline = true

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        initData()
    }

    private fun setupView() {
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        supportActionBar!!.title = "filter"
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

    override fun onItemMenuClick(user: User) {
        viewModel.insertUserToLocal(user)
        viewModel.insertPet(user.id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView!!.maxWidth = Int.MAX_VALUE
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, "search $query", Toast.LENGTH_SHORT).show()
                return when (isOnline) {
                    true -> {
                        viewModel.getUserBySearch(query)
                        true
                    }
                    false -> {
                        viewModel.getUsersLocalBySearch(query)
                        true
                    }
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView!!.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                onBackPressed()
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_online -> {
                Toast.makeText(this, "online", Toast.LENGTH_SHORT).show()
                viewModel.getUsers()
                isOnline = true
                return true
            }
            R.id.action_offline -> {
                Toast.makeText(this, "offline", Toast.LENGTH_SHORT).show()
                viewModel.getUsersLocal()
                viewModel.getUserAndPet()
                isOnline = false
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        when (isOnline) {
            true -> {
                viewModel.getUsers()
                return
            }
            false -> {
                viewModel.getUsersLocal()
                return
            }
        }
    }
}
