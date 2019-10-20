package com.alexeykatsuro.piggybank.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.alexeykatsuro.piggybank.R
import com.alexeykatsuro.piggybank.databinding.FragmentHomeBinding
import com.alexeykatsuro.piggybank.ui.base.PiggyBankFragment
import com.alexeykatsuro.piggybank.utils.text
import com.alexeykatsuro.piggybank.utils.toCoins
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : PiggyBankFragment() {

    private lateinit var binding: FragmentHomeBinding

    val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            buttonFeed.setOnClickListener {
                navController.navigate(R.id.feederFragment)
            }
            buttonHistory.setOnClickListener {
                navController.navigate(R.id.historyFragment)
            }

            inputRate.setEndIconOnClickListener {
                val rate = inputRate.editText!!.text.toString().toFloatOrNull() ?: 0.0f
                viewModel.updateRate(rate)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profit.observe(viewLifecycleOwner, Observer {
            binding.profit.text = it.toString()
        })

        viewModel.rate.observe(viewLifecycleOwner, Observer {
            binding.inputRate.text = it.toCoins()
        })
    }
}