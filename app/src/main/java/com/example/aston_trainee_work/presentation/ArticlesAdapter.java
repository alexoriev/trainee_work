package com.example.aston_trainee_work.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.aston_trainee_work.databinding.ItemArticleBinding;
import com.example.aston_trainee_work.domain.ArticleItem;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends ListAdapter<ArticleItem, ArticlesViewHolder> {

/*    private List<ArticleItem> articlesList = new ArrayList<>();*/

    public ArticlesAdapter() {
        super(new ArticleDiffCallback());
    }

    public void setData(List<ArticleItem> articles) {
/*        articlesList = articles;*/
        submitList(articles);
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticleBinding binding = ItemArticleBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ArticlesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        ArticleItem article = getItem(position);
        holder.bind(article);
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