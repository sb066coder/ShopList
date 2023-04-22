package ru.sb066coder.shoplist.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import ru.sb066coder.shoplist.R

@BindingAdapter("setFloatToText")
fun setFloatToText(textView: TextView, number: Float) {
    textView.text = number.toString()
}

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.enter_item_name)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputAmount")
fun bindErrorInputAmount(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.enter_amount_more_than_zero)
    } else {
        null
    }
    textInputLayout.error = message
}