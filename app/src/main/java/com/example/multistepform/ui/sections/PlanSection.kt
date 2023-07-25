package com.example.multistepform.ui.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.multistepform.data.FormData
import com.example.multistepform.ui.Period
import com.example.multistepform.ui.Plan
import com.example.multistepform.ui.components.SectionHeader

@Composable
fun PlanSection(
    period: Period,
    changePeriod: (Period) -> Unit,
    selectedPlan: Plan,
    changeSelectedPlan: (Plan) -> Unit
) {
    SectionHeader(
        title = "Select your plan",
        description = "You have the option of monthly or yearly billing"
    )
    FormData.plans.forEach {
        PlanCard(
            it,
            period = period,
            isSelected = it == selectedPlan,
            changeSelectedPlan = changeSelectedPlan
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        PeriodText(expectedPeriod = Period.Monthly, period = period)
        Switch(
            checked = period == Period.Yearly,
            onCheckedChange = {
                if (it) {
                    changePeriod(Period.Yearly)
                } else {
                    changePeriod(Period.Monthly)
                }
            },
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        PeriodText(expectedPeriod = Period.Yearly, period = period)
    }
}

@Composable
fun PeriodText(
    expectedPeriod: Period,
    period: Period,
    modifier: Modifier = Modifier
) {
    if (period == expectedPeriod)
        Text(expectedPeriod.name, color = MaterialTheme.colorScheme.primary, modifier = modifier)
    else
        Text(expectedPeriod.name, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanCard(
    plan: Plan,
    modifier: Modifier = Modifier,
    period: Period,
    isSelected: Boolean,
    changeSelectedPlan: (Plan) -> Unit
) {
    OutlinedCard(
        onClick = { changeSelectedPlan(plan) },
        modifier = modifier
            .fillMaxWidth(),
        border = BorderStroke(
            width = if (isSelected) 2.dp else Dp.Hairline,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(painterResource(id = plan.iconId), contentDescription = null)
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    plan.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                when (period) {
                    Period.Monthly -> {
                        Text("$${plan.monthlyPrice}/mo")
                    }

                    Period.Yearly -> {
                        Text("$${plan.yearlyPrice}/yr")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("2 months free", style = MaterialTheme.typography.titleSmall)
                    }
                }
            }
        }
    }
}