@file:JvmName("Utils")

package com.alexeykatsuro.piggybank.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.IntRange
import androidx.databinding.adapters.ListenerUtil
import androidx.lifecycle.MutableLiveData
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.LeftAndRightComparer
import com.alexeykatsuro.piggybank.R
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

fun Float.toCoins(): String = String.format(Locale.US, "%.2f", this)

const val defaultDatePattern = "dd.MM.yyyy HH:mm"

@JvmOverloads
fun Date.toDateString(pattern: String = defaultDatePattern): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun <T : Any, R> DataSource<T>.simpleSet(items: List<T>, prop: T.() -> R) {
    val areTheSame: LeftAndRightComparer<T> = { left, right ->
        left.prop() == right.prop()
    }
    val areContentsTheSame: LeftAndRightComparer<T> = { left, right ->
        left == right
    }
    set(items, areTheSame, areContentsTheSame)
}

inline fun EditText.onPaddingTextChanged(
    @IntRange(from = 0, to = 10000)
    delay: Int,
    crossinline onChanged: (String) -> Unit
) {
    val onChangedCallback = Runnable { onChanged(text.trim().toString()) }

    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
            Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            removeCallbacks(onChangedCallback)
            if (delay == 0) {
                onChangedCallback.run()
            } else {
                postDelayed(onChangedCallback, delay.toLong())
            }
        }
    }
    val oldValue = ListenerUtil.trackListener<TextWatcher>(this, watcher, R.id.paddingWatcher)
    if (oldValue != null) {
        removeTextChangedListener(oldValue)
    }
    addTextChangedListener(watcher)
}

inline fun TextInputLayout.onPaddingTextChanged(
    @IntRange(from = 0, to = 10000)
    delay: Int,
    crossinline onChanged: (String) -> Unit
) {
    editText!!.onPaddingTextChanged(delay, onChanged)
}