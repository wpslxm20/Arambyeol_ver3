package com.arambyeol.todaymeal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.todayMealGraph(navController: NavController) {
    composable("today_meal_screen") {
        val viewModel: TodayMealViewModel = hiltViewModel()
        TodayMealScreen(viewModel)
    }
}

@Composable
fun TodayMealScreen(
    viewModel: TodayMealViewModel
) {
    val meals = viewModel.todayMeals.collectAsState()

//    LaunchedEffect(true) {
//        viewModel.loadMeals("2025-08-14")
//    }

    Text("오늘의 식단")
}