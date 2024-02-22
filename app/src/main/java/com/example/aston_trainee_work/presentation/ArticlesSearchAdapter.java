package com.example.aston_trainee_work.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aston_trainee_work.databinding.ItemArticleSearchBinding;
import com.example.aston_trainee_work.databinding.ItemLoadingBinding;
import com.example.aston_trainee_work.domain.ArticleItem;

import java.util.ArrayList;
import java.util.List;

public class ArticlesSearchAdapter extends ListAdapter<ArticleItem, RecyclerView.ViewHolder> {

    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoading = false;
    private OnArticleInteractionListener onArticleInteractionListener;

    public ArticlesSearchAdapter(OnArticleInteractionListener onArticleInteractionListener) {
        super(new ArticleDiffCallback());
        this.onArticleInteractionListener = onArticleInteractionListener;
    }

    public void setList(List<ArticleItem> articles) {
        submitList(articles);
    }

    public void addAll(List<ArticleItem> articles) {
        List<ArticleItem> currentList = new ArrayList<>(getCurrentList());
        currentList.addAll(articles);
        submitList(currentList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == LOADING) {
            ItemLoadingBinding loadingBinding = ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false);
            viewHolder = new LoadingViewHolder(loadingBinding);
        } else {
            ItemArticleSearchBinding itemBinding = ItemArticleSearchBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false);
            viewHolder = new ArticleSearchViewHolder(itemBinding, onArticleInteractionListener);
        }
        return viewHolder;

    }

    @Override
    public int getItemViewType(int position) {
        return (position == getItemCount() - 1 && isLoading)
                ? LOADING
                : ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM) {
            ArticleItem article = getItem(position);
            ((ArticleSearchViewHolder) holder).bind(article);
        }
    }

    public void showLoading() {
        isLoading = true;
    }

    public void hideLoading() {
        isLoading = false;
    }

    static class ArticleDiffCallback extends DiffUtil.ItemCallback<ArticleItem> {
        @Override
        public boolean areItemsTheSame(@NonNull ArticleItem oldItem,
                                       @NonNull ArticleItem newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticleItem oldItem, @NonNull ArticleItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}