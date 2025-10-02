package com.arambyeol.weeklymeal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.weeklyMealGraph(navController: NavController) {
    composable("weekly_meal_screen") {
        val viewModel: WeeklyMealViewModel = hiltViewModel()
        WeeklyMealScreen(viewModel)
    }
}

@Composable
fun WeeklyMealScreen(
    viewModel: WeeklyMealViewModel
) {
    Column {
        Text("주간 식단")
    }
}