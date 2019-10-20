@file:JvmName("Utils")

package com.alexeykatsuro.piggybank.utils

import androidx.lifecycle.MutableLiveData
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.LeftAndRightComparer
import com.alexeykatsuro.piggybank.data.entries.MealEntry
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

suspend fun <T> Flow<T>.toLiveData(mutableLiveData: MutableLiveData<T>) {
    collect {
        mutableLiveData.postValue(it)
    }
}

var TextInputLayout.text: String
    get() = editText!!.text.toString()
    set(value) {
        editText!!.setText(value)
    }

fun Float.toCoins(): String = String.format("%.2f", this)

const val defaultDatePattern = "dd.MM.yyyy hh:mm"

@JvmOverloads
fun Date.toDateString(pattern: String = defaultDatePattern): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun <T : Any, R> DataSource<T>.simpleSet(items: List<T>, prop : T.()->R){
    val areTheSame: LeftAndRightComparer<T> = { left, right ->
        left.prop() == right.prop()
    }
    val areContentsTheSame: LeftAndRightComparer<T> = { left, right ->
        left == right
    }
    set(items,areTheSame, areContentsTheSame)
}