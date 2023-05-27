package com.example.multistepform.data

import com.example.multistepform.ui.AddOn
import com.example.multistepform.ui.Plan

object FormData {
    val plans: List<Plan> = listOf(
        Plan("Arcade", 9, 90),
        Plan("Advanced", 12, 120),
        Plan("Pro", 15, 150)
    )

    val addOns: List<AddOn> = listOf(
        AddOn(
            "Online service", "Access to multiplayer games",
            1, 10
        ),
        AddOn(
            "Larger storage", "Extra 1TB of cloud save",
            2, 20
        ),
        AddOn(
            "Customizable profile", "Custom theme on your profile",
            2, 20
        )
    )
}