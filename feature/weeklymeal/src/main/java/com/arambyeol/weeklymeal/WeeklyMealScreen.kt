package com.arambyeol.weeklymeal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.hasText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arambyeol.core.ui.R
import com.arambyeol.domain.entity.MealType
import com.arambyeol.ui.component.DailyMealCard
import com.arambyeol.ui.component.MealErrorMessage
import com.arambyeol.ui.state.UiState
import com.arambyeol.ui.theme.DarkYellow
import java.time.DayOfWeek
import java.time.LocalDate

fun NavGraphBuilder.weeklyMealGraph(navController: NavController) {
    composable("weekly_meal_screen") {
//        val viewModel: WeeklyMealViewModel = hiltViewModel()
        WeeklyMealScreen()
    }
}

@Composable
fun WeeklyMealScreen(
    viewModel: WeeklyMealViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        WeekdaySelectBox(
            selectedDate = selectedDate,
            onDateSelected = { date -> viewModel.onDateSelected(date) }
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    // 아무 뷰도 없어도 됨
                }
                is UiState.Empty -> {
                    Text(text = stringResource(R.string.error_not_found))
                }
                is UiState.Error -> {
                    MealErrorMessage(error = (uiState as UiState.Error).message)
                }
                is UiState.Success -> {
                    val weeklyMeals = (uiState as UiState.Success).data
                    val selectedMeal = weeklyMeals.filter { it.date == selectedDate.toString() }

                    if (selectedMeal.isNotEmpty()) {
                        DailyMealCard(
                            meals = selectedMeal.first()
                        )
                    } else {
                        Text(text = stringResource(R.string.error_not_found))
                    }
                }
            }
        }
    }
}

@Composable
fun WeekdaySelectBox(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, top = 23.dp, end = 25.dp, bottom = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Gray.copy(alpha = 0.15f),
                            Color.Gray.copy(alpha = 0.1f)
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(end = 3.dp, bottom = 5.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp),
                )
        )

        var startOfWeek by remember {
            mutableStateOf(selectedDate.with(DayOfWeek.MONDAY))
        }

        // 이번주와 다음주 제한
        val thisMonday = LocalDate.now().with(DayOfWeek.MONDAY)
        val nextMonday = thisMonday.plusWeeks(1)

        val weekDates = (0..6).map { startOfWeek.plusDays(it.toLong()) }
        val weekDays = stringArrayResource(R.array.week_days)

        // 실제 컨텐츠
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_previous),
                contentDescription = "previous",
                modifier = Modifier
                    .width(8.dp)
                    .height(16.dp)
                    .clickable(enabled = startOfWeek != thisMonday) {
                        startOfWeek = startOfWeek.minusWeeks(1)
                        onDateSelected(startOfWeek)
                    }
                    .alpha(if (startOfWeek != thisMonday) 1f else 0.3f)
            )

            LazyRow(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items(weekDates) { date ->
                    val isSelected = date == selectedDate
                    val isToday = date == LocalDate.now()

                    val dayOfWeekText = weekDays[date.dayOfWeek.value - 1]
                    Column(
                        modifier = Modifier
                            .size(39.dp, 54.dp)
                            .background(if (isSelected) DarkYellow else Color.Transparent,RoundedCornerShape(12.dp))
                            .border(1.dp, if (isToday) DarkYellow else Color.Transparent, RoundedCornerShape(12.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onDateSelected(date) },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = dayOfWeekText,
                            fontSize = 14.sp,
                            color = if (isSelected) Color.White else Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${date.monthValue}/${date.dayOfMonth}",
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Icon(
                painter = painterResource(R.drawable.ic_next),
                contentDescription = "next",
                modifier = Modifier
                    .width(8.dp)
                    .height(16.dp)
                    .clickable(enabled = startOfWeek != nextMonday) {
                        startOfWeek = startOfWeek.plusWeeks(1)
                        // 선택된 날짜도 다음 주로 이동
                        onDateSelected(startOfWeek)
                    }
                    .alpha(if (startOfWeek != nextMonday) 1f else 0.3f)
            )
        }
    }
}