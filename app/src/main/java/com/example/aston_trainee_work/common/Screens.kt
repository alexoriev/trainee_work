package com.example.aston_trainee_work.common

import com.example.aston_trainee_work.presentation.HeadlinesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun headlines() = FragmentScreen{ HeadlinesFragment() }
}