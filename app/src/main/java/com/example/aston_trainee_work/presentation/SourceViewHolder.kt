package com.example.aston_trainee_work.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.aston_trainee_work.R
import com.example.aston_trainee_work.databinding.ItemSourceBinding
import com.example.aston_trainee_work.domain.SourceItem
import java.util.Locale

class SourceViewHolder(
    private val binding: ItemSourceBinding,
    private val onSourceInteractionListener: OnSourceInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(source: SourceItem) {
        binding.apply {
            sourceTv.text = source.name
            val format = root.resources.getString(R.string.source_category_country)
            val countryFullName = if (source.country == "us") {
                "USA"
            } else {
                Locale("", source.country).displayCountry
            }

            sourceCategoryCountry.text = String.format(format,
                convertFirstCharToUppercase(source.category),
                convertFirstCharToUppercase(countryFullName))

            sourceItem.setOnClickListener {
                onSourceInteractionListener.onGetArticlesBySource(source)
            }

            sourceIv.setImageResource(source.imageResourceId ?: R.drawable.source_placeholder)
        }
    }

    private fun convertFirstCharToUppercase(string: String): String {
        return string.replaceFirstChar { c -> c.uppercaseChar() }
    }
}