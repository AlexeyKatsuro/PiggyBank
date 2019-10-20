package com.alexeykatsuro.piggybank.ui.feeder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexeykatsuro.data.util.Event
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import com.alexeykatsuro.piggybank.data.repository.MealRepository
import com.alexeykatsuro.piggybank.ui.base.PiggyBankViewModel
import kotlinx.coroutines.launch

class FeederViewModel(private val mealRepository: MealRepository) :
    PiggyBankViewModel() {

    private val _afterMeal = MutableLiveData<Event<Unit>>()
    val afterMeal: LiveData<Event<Unit>>
        get() = _afterMeal

    fun feed(meal: MealEntry) {
        viewModelScope.launch {
            mealRepository.feed(meal)
            _afterMeal.value = Event(Unit)
        }
    }

}