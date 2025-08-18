package com.arambyeol.todaymeal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TodayMealScreen(
    viewModel: TodayMealViewModel = hiltViewModel()
) {
    val meals = viewModel.todayMeals.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMeals("2025-08-14")
    }

    print(meals.value)
}