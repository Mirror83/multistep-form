package com.example.multistepform.ui

import androidx.lifecycle.ViewModel
import com.example.multistepform.ui.sections.SectionConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MultiStepFormViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(MultiStepFormUiState())
    val uiState = _uiState.asStateFlow()

    fun goToNextScreen() {
        _uiState.value = _uiState.value.copy(
            currentSection = _uiState.value.currentSection.inc()
        )
    }

    fun goToPreviousScreen() {
        _uiState.value = _uiState.value.copy(
            currentSection = _uiState.value.currentSection.dec()
        )
    }

    fun goToPlanSection() {
        _uiState.value = _uiState.value.copy(
            currentSection = SectionConstants.PLAN
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

    fun changePeriod(period: Period) {
        _uiState.value = _uiState.value.copy(
            period = period
        )
    }

    fun changePlan(plan: Plan) {
        _uiState.value = _uiState.value.copy(
            plan = plan
        )
    }

    fun appendAddOn(addOn: AddOn) {
        _uiState.value = _uiState.value.copy(
            chosenAddOns = _uiState.value.chosenAddOns.plus(addOn)
        )
    }

    fun removeAddOn(addOn: AddOn) {
        _uiState.value = _uiState.value.copy(
            chosenAddOns = _uiState.value.chosenAddOns.minus(addOn)
        )
    }

    private fun isInvalidEmail(email: String): Boolean {
        val emailRegex =
            Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
        return !emailRegex.matches(email)
    }

    private fun isInvalidName(name: String): Boolean {
        return name.length < 2 && !Regex("\\p{Alpha}+").matches(name)
    }

}