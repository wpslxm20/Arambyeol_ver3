package com.arambyeol.arambyeol_ver3

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arambyeol.todaymeal.todayMealGraph

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "today_meal_screen"
    ) {
        // 오늘의 식단 (실제 구현)
        todayMealGraph(navController)

        // 주간 식단 (더미)
        composable("weekly_meal_screen") {
            Text("주간 식단 화면 (Dummy)")
        }

        // 내 후기 (더미)
        composable("review_screen") {
            Text("내 후기 화면 (Dummy)")
        }

        // 룸메이트 (더미)
        composable("roommate_screen") {
            Text("룸메이트 화면 (Dummy)")
        }

        // 채팅 (더미)
        composable("chat_screen") {
            Text("채팅 화면 (Dummy)")
        }
    }
}