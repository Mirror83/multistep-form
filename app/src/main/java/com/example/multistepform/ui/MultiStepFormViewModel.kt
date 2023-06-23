package com.example.multistepform.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MultiStepFormViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(MultiStepFormUiState())
    val uiState = _uiState.asStateFlow()

    fun goToNextScreen() {
        _uiState.value = _uiState.value.copy(
            currentStep = _uiState.value.currentStep.inc()
        )
    }

    fun goToPreviousScreen() {
        _uiState.value = _uiState.value.copy(
            currentStep = _uiState.value.currentStep.dec()
        )
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(
            personalInfo = _uiState.value.personalInfo.copy(
                name = name,
                isNameError = isInvalidName(name)
            )
        )
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(
            personalInfo = _uiState.value.personalInfo.copy(
                email = email,
                isEmailError = isInvalidEmail(email)
            )
        )
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(
            personalInfo = _uiState.value.personalInfo.copy(
                phoneNumber = phoneNumber
            )
        )
    }

    private fun isInvalidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
        return !emailRegex.matches(email)
    }

    private fun isInvalidName(name: String): Boolean {
        return name.length < 2 && !Regex("\\p{Alpha}+").matches(name)
    }

}