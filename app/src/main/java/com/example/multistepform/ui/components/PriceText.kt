package com.example.multistepform.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.multistepform.ui.Period

@Composable
fun PriceText(price: Int, period: Period, isAddon: Boolean = true) {
    val text = if (isAddon) "+" else ""
    when (period) {
        Period.Monthly -> Text(text + "$${price}/mo")
        Period.Yearly -> Text(text + "$${price}/yr")
    }
}