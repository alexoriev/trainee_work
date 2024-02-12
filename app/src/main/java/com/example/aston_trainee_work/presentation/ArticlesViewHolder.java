package com.example.aston_trainee_work.presentation;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aston_trainee_work.databinding.ItemArticleBinding;
import com.example.aston_trainee_work.domain.ArticleItem;

public class ArticlesViewHolder extends RecyclerView.ViewHolder {
    private final ItemArticleBinding binding;

    ArticlesViewHolder(ItemArticleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ArticleItem articleItem) {
        binding.articleHeadlineTv.setText(articleItem.getTitle());
        binding.articleSourceTv.setText(articleItem.getSource().getName());

        Glide.with(this.itemView.getContext())
                .load(articleItem.getUrlToImage())
                .into(binding.articleHeadlineIv);
    }
}
