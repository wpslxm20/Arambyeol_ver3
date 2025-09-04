package com.arambyeol.arambyeol_ver3

sealed class TopTabItem(val title: String, val route: String) {
    object TodayMeal : TopTabItem("오늘의 식단", "today_meal_screen")
    object WeeklyMeal : TopTabItem("주간 식단", "weekly_meal_screen")
    object Review : TopTabItem("내 후기", "review_screen")
    object Roommate : TopTabItem("룸메이트", "roommate_screen")
    object Chat : TopTabItem("채팅", "chat_screen")
}

val topTabs = listOf(
    TopTabItem.TodayMeal,
    TopTabItem.WeeklyMeal,
    TopTabItem.Review,
    TopTabItem.Roommate,
    TopTabItem.Chat
)
