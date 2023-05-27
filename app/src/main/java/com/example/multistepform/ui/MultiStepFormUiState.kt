package com.example.multistepform.ui

import com.example.multistepform.data.FormData

data class PersonalInfo (var name: String, val email: String, val phoneNumber: String)

data class Plan(val name: String, val monthlyPrice: Int, val yearlyPrice: Int)

data class AddOn(val name: String, val description: String, val monthlyPrice: Int, val yearlyPrice: Int)

enum class Period {
    Monthly, Yearly
}


data class MultiStepFormUiState (
    val currentStep: Int = 1,
    val personalInfo: PersonalInfo = PersonalInfo("", "", ""),
    val isFieldError: Boolean = false,
    val chosenAddOns: List<AddOn> = emptyList(),
    val plan: Plan = FormData.plans[0],
    val period: Period = Period.Monthly
)
