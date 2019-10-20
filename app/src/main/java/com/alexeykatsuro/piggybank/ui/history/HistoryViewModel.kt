package com.alexeykatsuro.piggybank.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import com.alexeykatsuro.piggybank.data.repository.MealRepository
import com.alexeykatsuro.piggybank.ui.base.PiggyBankViewModel
import com.alexeykatsuro.piggybank.utils.toLiveData
import kotlinx.coroutines.launch

class HistoryViewModel(private val mealRepository: MealRepository) : PiggyBankViewModel() {

    private val _history = MutableLiveData<List<MealEntry>>()
    val history: LiveData<List<MealEntry>>
        get() = _history

    init {
        viewModelScope.launch {
            mealRepository.getMealHistoryObserve().toLiveData(_history)
        }
    }
}