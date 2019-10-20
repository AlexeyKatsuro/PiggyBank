package com.alexeykatsuro.piggybank.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexeykatsuro.piggybank.data.preferences.PreferenceStorage
import com.alexeykatsuro.piggybank.data.repository.MealRepository
import com.alexeykatsuro.piggybank.ui.base.PiggyBankViewModel
import com.alexeykatsuro.piggybank.utils.toLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
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
            val nationalFlow = mealRepository.totalAmountNationalObserve()
            val foreignFlow = mealRepository.totalAmountForeignObserve()
            val rateFlow = preference.currentRateObserve

            @Suppress("EXPERIMENTAL_API_USAGE")
            combine(nationalFlow, foreignFlow, rateFlow) { national, foreign, rate ->
                calculateProfit(national, foreign, rate)
            }.toLiveData(_profit)
        }
        viewModelScope.launch {
            preference.currentRateObserve.collect {
                _rate.value = it
            }
        }
    }

    private fun calculateProfit(amountNational: Float, amountForeign: Float, rate: Float): Float {
        return amountForeign * rate - amountNational
    }

    fun updateRate(rate: Float) {
        preference.currentRate = rate
    }
}