package com.example.multistepform.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTextBox(
    modifier: Modifier = Modifier,
    text: String = "",
    value: String = "",
    placeholderText: String = "",
    onValueChanged: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    errorSupportingText: String = ""
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        Text(text, style = MaterialTheme.typography.labelSmall)
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
                value = value,
                onValueChange = { onValueChanged(it) },
                placeholder = {
                    Text(placeholderText)
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                isError = isError,
                supportingText = { if (isError) Text(errorSupportingText) }
            )
        Spacer(Modifier.height(10.dp))
    }
}