package com.example.aston_trainee_work.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aston_trainee_work.databinding.FragmentGeneralTabBinding;

public class GeneralTabFragment extends Fragment {

    private FragmentGeneralTabBinding binding;

    public GeneralTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeneralTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}