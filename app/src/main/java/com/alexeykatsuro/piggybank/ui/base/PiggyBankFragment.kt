package com.alexeykatsuro.piggybank.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class PiggyBankFragment : Fragment(){

    val navController by lazy { findNavController() }

}