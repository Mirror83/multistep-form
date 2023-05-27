package com.example.multistepform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.multistepform.ui.MultiStepFormApp
import com.example.multistepform.ui.theme.MultiStepFormTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiStepFormTheme {
                MultiStepFormApp()
            }
        }
    }
}