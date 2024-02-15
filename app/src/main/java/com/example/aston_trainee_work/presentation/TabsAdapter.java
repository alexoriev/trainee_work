package com.example.aston_trainee_work.presentation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aston_trainee_work.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class TabsAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public TabsAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        initData();
    }

    private void initData() {
        addData(new HeadlinesTabFragment(Category.GENERAL), "General");
        addData(new HeadlinesTabFragment(Category.BUSINESS), "Business");
        addData(new HeadlinesTabFragment(Category.TECHNOLOGY), "Technology");
    }

    private void addData(Fragment tabFragment, String title) {
        fragments.add(tabFragment);
        titles.add(title);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public String getTitle(int position) {
        return titles.get(position);
    }
}
