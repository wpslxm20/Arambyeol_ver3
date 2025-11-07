package com.arambyeol.todaymeal

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.arambyeol.ui.component.MealErrorMessage
import com.arambyeol.ui.state.UiError
import com.arambyeol.ui.state.UiState

fun NavGraphBuilder.todayMealGraph(navController: NavController) {
    composable("today_meal_screen") {
//        val viewModel: TodayMealViewModel = hiltViewModel()
        TodayMealScreen()
    }
}

@Composable
fun TodayMealScreen(
    viewModel: TodayMealViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TodayDateBox(viewModel.getTodayFormatted())
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
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
                    DailyMealCard(
                        meals = (uiState as UiState.Success).data
                    )
                }
            }
        }
    }
}

@Composable
fun TodayDateBox(date: String) {
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

        // 실제 콘텐츠
        Row(
            modifier = Modifier
                .height(84.dp)
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
                    lineHeight = 14.sp
                )
                Text(
                    text = date,
                    color = Color.Black,
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            }
        }
    }
}
