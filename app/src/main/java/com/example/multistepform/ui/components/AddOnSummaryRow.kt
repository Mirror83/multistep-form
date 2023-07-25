package com.example.multistepform.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.multistepform.ui.AddOn
import com.example.multistepform.ui.Period

@Composable
fun AddOnSummaryRow(addOn: AddOn, period: Period) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(addOn.name)
        when (period) {
            Period.Monthly -> PriceText(price = addOn.monthlyPrice, period = period)
            Period.Yearly -> PriceText(price = addOn.yearlyPrice, period = period)
        }
    }

}
