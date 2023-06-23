package com.example.multistepform.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multistepform.R
import com.example.multistepform.ui.components.FormProgressIndicator
import com.example.multistepform.ui.components.InfoTextBox
import com.example.multistepform.ui.components.SectionHeader

@Composable
fun MultiStepFormApp(
    modifier: Modifier = Modifier,
    viewModel: MultiStepFormViewModel = viewModel()
) {
    val multiStepFormUiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.bg_sidebar_mobile),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.weight(0.8f)
                ) {
                    FormProgressIndicator(currentStep = multiStepFormUiState.currentStep)
                    Spacer(modifier = Modifier.padding(24.dp))
                    ElevatedCard(
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            when (multiStepFormUiState.currentStep) {
                                1 -> PersonalInfoSection(
                                    name = multiStepFormUiState.personalInfo.name,
                                    email = multiStepFormUiState.personalInfo.email,
                                    phoneNumber = multiStepFormUiState.personalInfo.phoneNumber,
                                    isNameError = multiStepFormUiState.personalInfo.isNameError,
                                    isEmailError = multiStepFormUiState.personalInfo.isEmailError,
                                    isPhoneError = multiStepFormUiState.personalInfo.isPhoneError,
                                    viewModel = viewModel,
                                )

                                2 -> PlanSection()
                                3 -> AddOnSection()
                                4 -> FinishingUpSection()
                                else -> ThankYouSection()
                            }

                        }
                    }
                }

                if (multiStepFormUiState.currentStep <= 4) {
                    StepNavigation(
                        currentStep = multiStepFormUiState.currentStep,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun StepNavigation(
    currentStep: Int, viewModel: MultiStepFormViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement =
        if (currentStep == 1)
            Arrangement.End
        else
            Arrangement.SpaceBetween,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        if (currentStep > 1) {
            TextButton(
                onClick = { viewModel.goToPreviousScreen() },
                shape = MaterialTheme.shapes.small
            ) {
                Text("Go back")
            }
        }
        Button(
            onClick = { viewModel.goToNextScreen() },
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = if (currentStep == 4) "Confirm" else "Next Step",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun PersonalInfoSection(
    name: String,
    email: String,
    phoneNumber: String,
    isPhoneError: Boolean = false,
    isEmailError: Boolean = false,
    isNameError: Boolean = false,
    viewModel: MultiStepFormViewModel
) {
    // TODO: Try to derive the regular expression for a valid email address
//    val emailRegExp = Regex("[a-z]*@[]")
    SectionHeader(
        title = "Personal info",
        description = "Please provide your name, email address and phone number."
    )

    InfoTextBox (
        text = "Name",
        value = name,
        onValueChanged = { viewModel.updateName(it) },
        placeholderText = "e.g Stephen King",
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        isError = isNameError,
        errorSupportingText = "Please enter a valid name"
    )

    InfoTextBox(
        text = "Email address",
        value = email,
        onValueChanged = { viewModel.updateEmail(it) },
        placeholderText = "e.g. stephenking@lorem.com",
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        isError = isEmailError,
        errorSupportingText = "Please input a valid email address"
    )

    InfoTextBox(
        text = "Phone number",
        value = phoneNumber,
        onValueChanged = { viewModel.updatePhoneNumber(it) },
        placeholderText = "e.g. +1 234 567 890",
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        isError = isPhoneError,
        errorSupportingText = "Please enter a valid phone number"
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanSection() {
    SectionHeader(
        title = "Select your plan",
        description = "You have the option of monthly or yearly billing"
    )

    OutlinedCard(onClick = { /*TODO*/ }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) {
                Image(painterResource(id = R.drawable.icon_checkmark), contentDescription = null)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(3f)
            ) {
                Text("Arcade")
                Text("$9/mo")
            }
        }
    }
}

@Composable
fun AddOnSection() {
    SectionHeader(
        title = "Pick add-ons",
        description = "Add-ons help enhance your gaming experience"
    )
}

@Composable
fun FinishingUpSection() {
    Text("Finishing up", style = MaterialTheme.typography.titleLarge)
}

@Composable
fun ThankYouSection() {
    Text("Thank you", style = MaterialTheme.typography.titleLarge)
}

@Preview
@Composable
fun MultiStepFormPreview() {
    MultiStepFormApp()
}



