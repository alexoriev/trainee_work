package com.example.aston_trainee_work.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.example.aston_trainee_work.R;
import com.example.aston_trainee_work.databinding.FragmentHeadlinesBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class HeadlinesFragment extends Fragment {

    private FragmentHeadlinesBinding binding = null;
    private TabsAdapter adapter;
    int[] imageList = {R.drawable.general, R.drawable.business, R.drawable.technology};

    public FragmentHeadlinesBinding getBinding() {
        return binding;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("News APP");
        ((MainActivity) getActivity()).showActionBar();

        binding = FragmentHeadlinesBinding.inflate(inflater, container, false);

        adapter = new TabsAdapter(requireActivity().getSupportFragmentManager(), getLifecycle());
        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabLayout,
                binding.viewPager,
                (tab, position) -> {
                    tab.setText(adapter.getTitle(position));
                    tab.setIcon(AppCompatResources.getDrawable(requireContext(), imageList[position]));
                });

        binding.viewPager.setAdapter(adapter);

        if (!mediator.isAttached()) {
            mediator.attach();
        }

        return getBinding().getRoot();
    }
}