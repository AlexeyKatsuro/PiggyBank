package com.alexeykatsuro.piggybank.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.alexeykatsuro.piggybank.R
import com.alexeykatsuro.piggybank.databinding.FragmentHomeBinding
import com.alexeykatsuro.piggybank.ui.base.PiggyBankFragment
import com.alexeykatsuro.piggybank.utils.onPaddingTextChanged
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
                val rate = inputRate.text.toFloat()
                viewModel.updateRate(rate)
            }

            inputRate.onPaddingTextChanged(1500) {
                val rate = it.toFloatOrNull()
                if (rate != null)
                    viewModel.updateRate(rate)
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profit.observe(viewLifecycleOwner, Observer {
            binding.profit.text = it.toCoins()
        })

        viewModel.rate.observe(viewLifecycleOwner, Observer {
            if (binding.inputRate.text.toFloatOrNull() != it) {
                binding.inputRate.text = it.toCoins()
            }
        })
    }
}