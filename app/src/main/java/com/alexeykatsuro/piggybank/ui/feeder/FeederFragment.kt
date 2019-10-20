package com.alexeykatsuro.piggybank.ui.feeder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexeykatsuro.data.util.EventObserver
import com.alexeykatsuro.piggybank.data.entities.Currency
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import com.alexeykatsuro.piggybank.databinding.FragmentFeederBinding
import com.alexeykatsuro.piggybank.ui.base.PiggyBankFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FeederFragment : PiggyBankFragment() {

    private lateinit var binding: FragmentFeederBinding

    val viewModel: FeederViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeederBinding.inflate(inflater, container, false).apply {

            buttonFeed.setOnClickListener {
                viewModel.feed(assembleMeal())
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.afterMeal.observe(viewLifecycleOwner, EventObserver{
            navController.navigateUp()
        })
    }

    private fun assembleMeal(): MealEntry {
        val amount = binding.inputAmountForeign.editText!!.toString().toFloatOrNull() ?: 0f
        val rate = binding.inputRate!!.toString().toFloatOrNull() ?: 0f

        return MealEntry(
            amountForeign = amount,
            amountNational = amount * rate,
            rate = rate,
            mealtime = Date(),
            currency = Currency.USD
        )
    }
}