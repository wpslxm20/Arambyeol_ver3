package com.arambyeol.todaymeal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arambyeol.ui.theme.TransparentYellow
import com.arambyeol.core.ui.R
import com.arambyeol.domain.entity.MealType
import com.arambyeol.ui.component.DailyMealCard

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

    LaunchedEffect(true) {
//        viewModel.loadDummyMeals()
        viewModel.loadTodayMeals()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TodayDateBox(viewModel.getTodayFormatted())
        DailyMealCard(meals, MealType.BREAKFAST)
    }
}

@Composable
fun TodayDateBox(date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, top = 23.dp, end = 25.dp, bottom = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Gray.copy(alpha = 0.15f), Color.Gray.copy(alpha = 0.1f))
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

        // 실제 콘텐츠
        Row(
            modifier = Modifier
                .padding(start = 27.dp, top = 20.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = "calendar icon",
                modifier = Modifier
                    .width(31.dp)
                    .height(34.dp),
                tint = Color.Unspecified, // 원래 색상 유지
            )

            Column(
                modifier = Modifier.padding(start = 18.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.today_meal),
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = date,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}
