package com.example.aston_trainee_work.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aston_trainee_work.databinding.FragmentFiltersBinding
import com.google.android.material.datepicker.MaterialDatePicker


class FiltersFragment : Fragment() {
    private lateinit var binding: FragmentFiltersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).apply {
            hideActionBar()
        }
        binding = FragmentFiltersBinding.inflate(inflater, container, false)

        binding.toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            // Respond to button selection
        }

        binding.dateTimePickerLl.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .build()
            fragmentManager?.let { it1 -> datePicker.show(it1, "tag") }
        }

        return binding.root
    }
}