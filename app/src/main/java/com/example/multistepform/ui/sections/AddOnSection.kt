package com.example.multistepform.ui.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.multistepform.data.FormData
import com.example.multistepform.ui.AddOn
import com.example.multistepform.ui.MultiStepFormViewModel
import com.example.multistepform.ui.Period
import com.example.multistepform.ui.components.SectionHeader

@Composable
fun AddOnSection(
    viewModel: MultiStepFormViewModel,
    period: Period = Period.Monthly,
    selectedAddOnsList: List<AddOn> = emptyList(),
) {
    SectionHeader(
        title = "Pick add-ons",
        description = "Add-ons help enhance your gaming experience"
    )

    FormData.addOns.forEach { addOn ->
        AddOnCard(
            addOn = addOn,
            period = period,
            isSelected = selectedAddOnsList.contains(addOn),
            appendToList = viewModel::appendAddOn,
            removeFromList = viewModel::removeAddOn,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

}


@Composable
fun AddOnCard(
    addOn: AddOn,
    period: Period,
    appendToList: (AddOn) -> Unit,
    removeFromList: (AddOn) -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    OutlinedCard(
        border = BorderStroke(
            width = if (isSelected) 2.dp else Dp.Hairline,
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(checked = isSelected, onCheckedChange = { checked ->
                    if (checked) appendToList(addOn) else removeFromList(addOn)
                })
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(3f)
            ) {
                Text(addOn.name, style = MaterialTheme.typography.titleMedium)
                Text(addOn.description, style = MaterialTheme.typography.bodySmall)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                when (period) {
                    Period.Monthly -> Text("+$${addOn.monthlyPrice}/mo")
                    Period.Yearly -> Text("+$${addOn.yearlyPrice}/yr")
                }
            }
        }
    }
}