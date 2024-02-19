package com.example.aston_trainee_work.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston_trainee_work.databinding.FragmentSavedBinding


class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).apply {
            setActionBarTitle("Saved")
            showActionBar()
        }.showActionBar()

        binding = FragmentSavedBinding.inflate(inflater, container, false)

        return binding.root
    }

}