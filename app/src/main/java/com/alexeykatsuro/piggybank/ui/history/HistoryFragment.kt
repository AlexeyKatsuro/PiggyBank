package com.alexeykatsuro.piggybank.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.LeftAndRightComparer
import com.afollestad.recyclical.datasource.emptyDataSourceTyped
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.alexeykatsuro.piggybank.R
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import com.alexeykatsuro.piggybank.databinding.FragmentHistoryBinding
import com.alexeykatsuro.piggybank.ui.base.PiggyBankFragment
import com.alexeykatsuro.piggybank.utils.simpleSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HistoryFragment : PiggyBankFragment() {

    private lateinit var binding: FragmentHistoryBinding

    private val dataSource: DataSource<MealEntry> = emptyDataSourceTyped()

    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mealHistoryRv.setup {
            withDataSource(dataSource)
            withItem<MealEntry, MealHolder>(R.layout.item_meal) {
                onBind(::MealHolder) { index: Int, item: MealEntry ->
                    bind(item)
                }
            }
        }

        viewModel.history.observe(viewLifecycleOwner, Observer {
            dataSource.simpleSet(it, MealEntry::id)
            dataSource.set(it)
        })
    }



}