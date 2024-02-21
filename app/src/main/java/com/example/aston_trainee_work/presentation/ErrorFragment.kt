package com.example.aston_trainee_work.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aston_trainee_work.databinding.FragmentErrorBinding

class ErrorFragment : Fragment() {
    private lateinit var binding: FragmentErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorBinding.inflate(inflater, container, false)

        binding.errorIv.setOnClickListener {

        }

        return binding.root
    }
}