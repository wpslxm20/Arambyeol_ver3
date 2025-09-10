package com.arambyeol.todaymeal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.entity.MealType
import com.arambyeol.domain.entity.Menu
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

    fun loadDummyMeals() {
        val dummyMeal = Meal(
            date = "2025-09-09",
            menusByMealType = mapOf(
                MealType.DINNER to listOf(
                    Menu(16, "쌀밥", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                    Menu(603, "콩가루배추국", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                    Menu(511, "적어구이*양념장", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                    Menu(499, "미니돈까스*케찹", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                    Menu(200, "오이양파무침", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                    Menu(6, "배추김치", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                    Menu(117, "미숫가루", MealType.DINNER.name, "A코스/한식", "", 0.0, 0),
                ),
                MealType.BREAKFAST to listOf(
                    Menu(1, "쌀밥 / 누룽지", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                    Menu(419, "홍고추콩나물국", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                    Menu(523, "돈육모듬장조림", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                    Menu(407, "꽈리고추멸치볶음", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                    Menu(585, "만다린샐러드*키위D", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                    Menu(6, "배추김치", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                    Menu(7, "모듬음료", MealType.BREAKFAST.name, "A코스/한식", "", 0.0, 0),
                    Menu(171, "페퍼로니피자빵", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(172, "시리얼", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(10, "떠먹는요거트", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(585, "만다린샐러드*키위D", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(7, "모듬음료", MealType.BREAKFAST.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(833, "크레미게살죽", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                    Menu(407, "꽈리고추멸치볶음", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                    Menu(585, "만다린샐러드*키위D", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                    Menu(6, "배추김치", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                    Menu(7, "모듬음료", MealType.BREAKFAST.name, "C코스/죽식", "", 0.0, 0),
                    Menu(64, "샌드위치", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                    Menu(196, "시리얼바", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                    Menu(7, "모듬음료", MealType.BREAKFAST.name, "테이크아웃", "", 0.0, 0),
                ),
                MealType.LUNCH to listOf(
                    Menu(16, "쌀밥", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                    Menu(25, "순두부찌개", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                    Menu(186, "한식잡채", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                    Menu(538, "부추겉절이", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                    Menu(6, "배추김치", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                    Menu(129, "망고주스", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                    Menu(417, "얼큰쌀국수", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(864, "설탕꽈배기", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(865, "삶은계란부추겉절이", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(6, "배추김치", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(129, "망고주스", MealType.LUNCH.name, "B코스/베이커리", "", 0.0, 0),
                    Menu(877, "할라피뇨크림미트볼", MealType.LUNCH.name, "A코스/한식", "", 0.0, 0),
                )
            )
        )
        _todayMeals.value = dummyMeal
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