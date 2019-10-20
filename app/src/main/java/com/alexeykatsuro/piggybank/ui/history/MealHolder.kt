package com.alexeykatsuro.piggybank.ui.history

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import com.alexeykatsuro.piggybank.databinding.ItemMealBinding

class MealHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemMealBinding.bind(itemView)

    fun bind(item: MealEntry) {
        binding.item = item
        binding.executePendingBindings()
    }

}