package com.example.multistepform.ui.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.multistepform.ui.AddOn
import com.example.multistepform.ui.MultiStepFormUiState
import com.example.multistepform.ui.Period
import com.example.multistepform.ui.Plan
import com.example.multistepform.ui.components.AddOnSummaryRow
import com.example.multistepform.ui.components.PriceText
import com.example.multistepform.ui.components.SectionHeader

@Composable
fun FinishingUpSection(multiStepFormUiState: MultiStepFormUiState, backToPlanSection: () -> Unit) {
    SectionHeader(
        title = "Finishing up", description = "Double-check everything looks OK before confirming"
    )
    SummaryCard(
        multiStepFormUiState.plan,
        multiStepFormUiState.chosenAddOns,
        multiStepFormUiState.period,
        backToPlanSection
    )
}

@Composable
fun SummaryCard(
    plan: Plan, chosenAddOns: List<AddOn>, period: Period, backToPlanSection: () -> Unit
) {
    OutlinedCard(modifier = Modifier.padding(horizontal = 8.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            when (period) {
                Period.Monthly -> {
                    Column {
                        Text("${plan.name} (Monthly)")
                        Text("Change",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable(enabled = true) { backToPlanSection() })
                    }
                    PriceText(
                        price = plan.monthlyPrice, period = period, isAddon = false
                    )
                }

                Period.Yearly -> {
                    Column {
                        Text("${plan.name} (Yearly)")
                        Text("Change", textDecoration = TextDecoration.Underline)
                    }
                    PriceText(
                        price = plan.yearlyPrice, period = period, isAddon = false
                    )
                }
            }
        }
        Divider()
        chosenAddOns.forEach {
            AddOnSummaryRow(
                addOn = it, period = period
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text("Total (per month)")
        when (period) {
            Period.Monthly -> PriceText(
                price = calculateTotal(
                    chosenAddOns, plan, period
                ), period = period, isAddon = false
            )

            Period.Yearly -> PriceText(
                price = calculateTotal(
                    chosenAddOns, plan, period
                ), period = period, isAddon = false
            )
        }
    }
}

private fun calculateTotal(chosenAddOns: List<AddOn>, plan: Plan, period: Period): Int {
    return when (period) {
        Period.Monthly -> {
            chosenAddOns.fold(0) { acc, addOn -> acc + addOn.monthlyPrice } + plan.monthlyPrice
        }

        Period.Yearly -> {
            chosenAddOns.fold(0) { acc, addOn -> acc + addOn.yearlyPrice } + plan.yearlyPrice
        }
    }
}