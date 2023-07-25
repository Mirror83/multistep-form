package com.example.multistepform.ui

import androidx.annotation.DrawableRes
import com.example.multistepform.data.FormData
import com.example.multistepform.ui.sections.SectionConstants

data class PersonalInfo(
    var name: String,
    val email: String,
    val phoneNumber: String,
    val isNameError: Boolean = false,
    val isPhoneError: Boolean = false,
    val isEmailError: Boolean = false,
)

fun PersonalInfo.isValid() : Boolean {
    return name.isNotBlank() && email.isNotBlank() && phoneNumber.isNotBlank()
}

data class Plan(
    @DrawableRes val iconId: Int,
    val name: String,
    val monthlyPrice: Int,
    val yearlyPrice: Int
)

data class AddOn(
    val name: String,
    val description: String,
    val monthlyPrice: Int,
    val yearlyPrice: Int
)

enum class Period {
    Monthly, Yearly
}


data class MultiStepFormUiState(
    val currentSection: Int = SectionConstants.PERSONAL_INF0,
    val personalInfo: PersonalInfo = PersonalInfo("", "", ""),
    val chosenAddOns: List<AddOn> = emptyList(),
    val plan: Plan = FormData.plans[0],
    val period: Period = Period.Monthly
)
