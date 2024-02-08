package com.example.aston_trainee_work.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.aston_trainee_work.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().run {
            val fragment = HeadlinesFragment()
            setReorderingAllowed(true)
            add(R.id.fragment_container, fragment)
            commit()
        }
    }
}