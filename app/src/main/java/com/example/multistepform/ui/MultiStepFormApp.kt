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
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multistepform.R
import com.example.multistepform.ui.components.FormProgressIndicator
import com.example.multistepform.ui.sections.AddOnSection
import com.example.multistepform.ui.sections.FinishingUpSection
import com.example.multistepform.ui.sections.PersonalInfoSection
import com.example.multistepform.ui.sections.PlanSection
import com.example.multistepform.ui.sections.SectionConstants
import com.example.multistepform.ui.sections.ThankYouSection

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
                    if (multiStepFormUiState.currentSection <= 4)
                        FormProgressIndicator(currentStep = multiStepFormUiState.currentSection)
                    Spacer(modifier = Modifier.padding(24.dp))
                    MultiStepFormAppContent(multiStepFormUiState, viewModel)
                }
                if (multiStepFormUiState.currentSection <= 4) {
                    StepNavigation(
                        currentStep = multiStepFormUiState.currentSection,
                        viewModel = viewModel,
                        personalInfoIsValid = multiStepFormUiState.personalInfo.isValid()
                    )
                }
            }
        }
    }
}

@Composable
fun MultiStepFormAppContent(
    multiStepFormUiState: MultiStepFormUiState,
    viewModel: MultiStepFormViewModel
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = if (multiStepFormUiState.currentSection <= 4) Alignment.Start
            else Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            when (multiStepFormUiState.currentSection) {
                1 -> PersonalInfoSection(
                    personalInfo = multiStepFormUiState.personalInfo,
                    viewModel = viewModel,
                )

                2 -> PlanSection(
                    period = multiStepFormUiState.period,
                    changePeriod = viewModel::changePeriod,
                    selectedPlan = multiStepFormUiState.plan,
                    changeSelectedPlan = viewModel::changePlan,
                )

                3 -> AddOnSection(
                    viewModel = viewModel,
                    period = multiStepFormUiState.period,
                    selectedAddOnsList = multiStepFormUiState.chosenAddOns
                )

                4 -> FinishingUpSection(
                    multiStepFormUiState = multiStepFormUiState,
                    backToPlanSection = viewModel::goToPlanSection
                )
                else -> ThankYouSection()
            }

        }
    }
}


@Composable
fun StepNavigation(
    currentStep: Int,
    personalInfoIsValid: Boolean,
    viewModel: MultiStepFormViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (currentStep == SectionConstants.PERSONAL_INF0) Arrangement.End
        else Arrangement.SpaceBetween,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        if (currentStep != SectionConstants.PERSONAL_INF0) {
            TextButton(
                onClick = { viewModel.goToPreviousScreen() },
                shape = MaterialTheme.shapes.small
            ) {
                Text("Go back")
            }
        }
        Button(
            onClick = { viewModel.goToNextScreen() },
            enabled = if (currentStep == SectionConstants.PERSONAL_INF0)
                personalInfoIsValid else true,
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = if (currentStep == SectionConstants.FINISHING_UP) "Confirm" else "Next Step",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiStepFormPreview() {
    MultiStepFormApp()
}



