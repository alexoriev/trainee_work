package com.example.aston_trainee_work.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.aston_trainee_work.databinding.ItemSourceBinding
import com.example.aston_trainee_work.domain.SourceItem

class SourcesAdapter(
    private val onSourceInteractionListener: OnSourceInteractionListener
) : ListAdapter<SourceItem, SourceViewHolder>(SourceDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val binding = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SourceViewHolder(binding, onSourceInteractionListener)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val source = getItem(position)
        holder.bind(source)
    }
}

class SourceDiffCallback : DiffUtil.ItemCallback<SourceItem>() {
    override fun areItemsTheSame(oldItem: SourceItem, newItem: SourceItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SourceItem, newItem: SourceItem): Boolean {
        return oldItem == newItem
    }
}