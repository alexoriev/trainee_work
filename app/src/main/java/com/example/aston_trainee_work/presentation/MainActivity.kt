package com.example.aston_trainee_work.presentation

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import com.example.aston_trainee_work.R
import com.example.aston_trainee_work.common.ArticlesApp
import com.example.aston_trainee_work.common.Screens.filters
import com.example.aston_trainee_work.common.Screens.headlines
import com.example.aston_trainee_work.common.Screens.saved
import com.example.aston_trainee_work.common.Screens.searchArticles
import com.example.aston_trainee_work.common.Screens.sources
import com.example.aston_trainee_work.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding

    private val navigator: Navigator = object : AppNavigator(this, R.id.fragment_container) {

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ArticlesApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigation

        val router = ArticlesApp.INSTANCE.appComponent.getRouter()
        router.navigateTo(headlines())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.headlines -> router.navigateTo(headlines())
                R.id.saved -> router.navigateTo(saved())
                R.id.sources -> router.navigateTo(sources())
            }
            return@setOnItemSelectedListener true
        }

        binding.search.setOnCloseListener {
            binding.filter.visibility = View.VISIBLE
            binding.topAppBar.navigationIcon = null
            router.exit()
            false
        }

        binding.search.setOnSearchClickListener {
            binding.filter.visibility = View.GONE
            binding.topAppBar.navigationIcon = getDrawable(R.drawable.ic_arrow_back)
        }

        var searchStarted = false
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (searchStarted) {
                    router.replaceScreen(searchArticles(query))
                } else {
                    router.navigateTo(searchArticles(query))
                }
                searchStarted = true
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (searchStarted) {
                    router.replaceScreen(searchArticles(newText))
                } else {
                    router.navigateTo(searchArticles(newText))
                }
                searchStarted = true
                return true
            }
        })

        binding.topAppBar.setNavigationOnClickListener {
            binding.search.isIconified = true
        }

        binding.filter.setOnClickListener {
            router.navigateTo(filters())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun setActionBarTitle(title: String) {
        binding.topAppBar.title = title
    }

    fun hideActionBar() {
        binding.appBarLayout.visibility = View.GONE
    }

    fun showActionBar() {
        binding.appBarLayout.visibility = View.VISIBLE
    }

    fun isConnected(): Boolean {
        var connected = false
        try {
            val cm =
                applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            e.message?.let { Log.e("Connectivity Exception", it) }
        }
        return connected
    }
}