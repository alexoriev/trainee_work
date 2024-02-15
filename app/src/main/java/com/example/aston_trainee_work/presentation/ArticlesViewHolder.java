package com.example.aston_trainee_work.presentation;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aston_trainee_work.R;
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

        Integer imageSourceId = articleItem.getSource().getImageSourceId();
        if (imageSourceId != null) {
            binding.articleSourceIv.setImageResource(articleItem.getSource().getImageSourceId());
        } else {
            binding.articleSourceIv.setImageResource(R.drawable.source_placeholder);
        }


        Glide.with(this.itemView.getContext())
                .load(articleItem.getUrlToImage())
                .centerCrop()
                .error(R.drawable.headlines_placeholder)
                .into(binding.articleHeadlineIv);
    }
}
