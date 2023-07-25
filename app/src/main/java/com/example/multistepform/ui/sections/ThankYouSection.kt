package com.example.multistepform.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.multistepform.R

@Composable
fun ThankYouSection() {
    Spacer(modifier = Modifier.height(64.dp))
    Image(painterResource(id = R.drawable.icon_thank_you), contentDescription = null)
    Spacer(modifier = Modifier.height(24.dp))
    Text("Thank you", style = MaterialTheme.typography.titleLarge)
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.confirmation_text),
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(64.dp))
}