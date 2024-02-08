package com.example.aston_trainee_work.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.aston_trainee_work.R
import com.example.aston_trainee_work.common.NewsApp
import com.example.aston_trainee_work.common.Screens.headlines
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = object : AppNavigator(this, R.id.fragment_container) {

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        NewsApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator.applyCommands(arrayOf<Command>(Replace(headlines())))

/*        supportFragmentManager.beginTransaction().run {
            val fragment = HeadlinesFragment()
            setReorderingAllowed(true)
            add(R.id.fragment_container, fragment)
            commit()
        }*/
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}