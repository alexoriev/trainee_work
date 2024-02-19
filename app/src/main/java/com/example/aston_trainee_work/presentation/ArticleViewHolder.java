package com.example.aston_trainee_work.presentation;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aston_trainee_work.R;
import com.example.aston_trainee_work.databinding.ItemArticleBinding;
import com.example.aston_trainee_work.domain.ArticleItem;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private final ItemArticleBinding binding;
    private final OnArticleInteractionListener onArticleInteractionListener;

    ArticleViewHolder(ItemArticleBinding binding,
                      OnArticleInteractionListener onArticleInteractionListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.onArticleInteractionListener = onArticleInteractionListener;
    }

    public void bind(ArticleItem articleItem) {
        binding.articleHeadlineTv.setText(articleItem.getTitle());
        binding.articleSourceTv.setText(articleItem.getSource().getName());

        Integer imageSourceId = articleItem.getSource().getImageResourceId();
        if (imageSourceId != null) {
            binding.articleSourceIv.setImageResource(articleItem.getSource().getImageResourceId());
        } else {
            binding.articleSourceIv.setImageResource(R.drawable.source_placeholder);
        }

        Glide.with(this.itemView.getContext())
                .load(articleItem.getUrlToImage())
                .centerCrop()
                .placeholder(R.drawable.no_image_placeholder)
                .into(binding.articleHeadlineIv);

        binding.articleItem.setOnClickListener(
                view -> onArticleInteractionListener.onOpenArticleProfile(articleItem));
    }
}
