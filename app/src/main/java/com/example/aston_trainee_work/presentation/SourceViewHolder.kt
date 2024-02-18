package com.example.aston_trainee_work.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.aston_trainee_work.databinding.ItemSourceBinding
import com.example.aston_trainee_work.domain.SourceItem

class SourceViewHolder(
    private val binding: ItemSourceBinding,
    private val onSourceInteractionListener: OnSourceInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(source: SourceItem) {
        binding.apply {

        }
    }
}