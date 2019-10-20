package com.alexeykatsuro.piggybank.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexeykatsuro.piggybank.data.preferences.PreferenceStorage
import com.alexeykatsuro.piggybank.data.repository.MealRepository
import com.alexeykatsuro.piggybank.ui.base.PiggyBankViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mealRepository: MealRepository,
    private val preference: PreferenceStorage
) : PiggyBankViewModel() {

    private val _profit = MutableLiveData<Float>()
    val profit: LiveData<Float>
        get() = _profit

    private val _rate = MutableLiveData<Float>()
    val rate: LiveData<Float>
        get() = _rate

    init {
        viewModelScope.launch {
            mealRepository.totalAmountNationalObserve().collect { national ->
                val foreign = mealRepository.totalAmountForeign()
                val rate = preference.currentRate
                _profit.value = calculateProfit(national,foreign, rate)
            }
        }
        viewModelScope.launch {
            _rate.value = preference.currentRate
        }
    }

    private fun calculateProfit(amountNational:Float,amountForeign:Float, rate:Float): Float {
        return amountForeign*rate - amountNational
    }

    fun updateRate(rate: Float){
        viewModelScope.launch {
            preference.currentRate = rate
            val foreign = mealRepository.totalAmountForeign()
            val national = mealRepository.totalAmountNational()
            _profit.value = calculateProfit(national,foreign, rate)
        }
    }
}