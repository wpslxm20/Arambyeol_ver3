package com.arambyeol.todaymeal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.usecase.GetMealsByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayMealViewModel @Inject constructor(
    private val getMealsByDateUseCase: GetMealsByDateUseCase
) : ViewModel() {
    private val _todayMeals = MutableStateFlow<Meal?>(null)
    val todayMeals: StateFlow<Meal?> = _todayMeals

    fun loadMeals(date: String) {
        viewModelScope.launch {
            _todayMeals.value = getMealsByDateUseCase(date)
        }
    }
}