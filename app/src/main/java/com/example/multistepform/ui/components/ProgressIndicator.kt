package com.example.multistepform.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.multistepform.ui.theme.LightBlue
import com.example.multistepform.ui.theme.White

@Composable
fun FormProgressIndicator(currentStep: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        repeat(4) {
            val stepNo = it + 1
            FormProgressItem(
                stepNo = stepNo,
                isCurrent = if (stepNo == 4) currentStep >= 4 else stepNo == currentStep
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}

@Composable
fun FormProgressItem(stepNo: Int, modifier: Modifier = Modifier, isCurrent: Boolean = false) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(40.dp)
            .width(40.dp)
            .drawBehind {
                if (isCurrent)
                    drawCircle(color = LightBlue)
                else
                    drawCircle(color = White, style = Stroke(width = 4f))
            }
    ) {
        Text(
            text = stepNo.toString(),
            color = if (isCurrent) MaterialTheme.colorScheme.primary else White
        )
    }
}