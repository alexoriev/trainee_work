package com.example.aston_trainee_work.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aston_trainee_work.common.ArticlesApp
import com.example.aston_trainee_work.databinding.FragmentNoInternetBinding

class NoInternetFragment : Fragment() {
    private lateinit var binding: FragmentNoInternetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoInternetBinding.inflate(inflater, container, false)

        val router = ArticlesApp.INSTANCE.appComponent.getRouter()

        binding.noInternetIv.setOnClickListener {
            if ((activity as MainActivity).isConnected()) {
                router.exit()
            }
        }

        return binding.root
    }
}