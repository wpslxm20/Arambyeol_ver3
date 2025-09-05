package com.arambyeol.todaymeal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.usecase.GetMealsByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import java.util.Locale

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

    fun getTodayFormatted(
        pattern: String = "yyyy년 MM월 dd일 E요일",
        locale: Locale = Locale("ko", "KR")
    ): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)
        return today.format(formatter)
    }
}