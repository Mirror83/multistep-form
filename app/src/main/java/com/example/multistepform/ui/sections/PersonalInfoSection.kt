package com.example.multistepform.ui.sections

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.multistepform.ui.MultiStepFormViewModel
import com.example.multistepform.ui.PersonalInfo
import com.example.multistepform.ui.components.InfoTextBox
import com.example.multistepform.ui.components.SectionHeader

@Composable
fun PersonalInfoSection(
    personalInfo: PersonalInfo,
    viewModel: MultiStepFormViewModel
) {
    SectionHeader(
        title = "Personal info",
        description = "Please provide your name, email address and phone number."
    )

    InfoTextBox(
        text = "Name",
        value = personalInfo.name,
        onValueChanged = { viewModel.updateName(it) },
        placeholderText = "e.g Stephen King",
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        isError = personalInfo.isNameError,
        errorSupportingText = "Please enter a valid name"
    )

    InfoTextBox(
        text = "Email address",
        value = personalInfo.email,
        onValueChanged = { viewModel.updateEmail(it) },
        placeholderText = "e.g. stephenking@lorem.com",
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        isError = personalInfo.isEmailError,
        errorSupportingText = "Please input a valid email address"
    )

    InfoTextBox(
        text = "Phone number",
        value = personalInfo.phoneNumber,
        onValueChanged = { viewModel.updatePhoneNumber(it) },
        placeholderText = "e.g. +1 234 567 890",
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        isError = personalInfo.isPhoneError,
        errorSupportingText = "Please enter a valid phone number"
    )

}