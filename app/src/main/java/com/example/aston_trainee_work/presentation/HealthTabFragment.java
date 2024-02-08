package com.example.aston_trainee_work.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aston_trainee_work.databinding.FragmentBusinessTabBinding;

public class HealthTabFragment extends Fragment {

    private FragmentBusinessTabBinding binding;

    public HealthTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBusinessTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}