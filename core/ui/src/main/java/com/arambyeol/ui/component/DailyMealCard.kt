package com.arambyeol.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arambyeol.core.ui.R
import com.arambyeol.domain.entity.Meal
import com.arambyeol.domain.entity.MealType
import com.arambyeol.domain.entity.Menu
import com.arambyeol.ui.theme.DarkGreen
import com.arambyeol.ui.theme.DarkRed
import com.arambyeol.ui.theme.DarkYellow
import com.arambyeol.ui.theme.Gray03
import com.arambyeol.ui.theme.Gray04
import com.arambyeol.ui.theme.Gray05
import com.arambyeol.ui.theme.LightYellow

@Composable
fun DailyMealCard(meals: Meal, mealTime: MealType) {
    meals.let { meal ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var selectedMeal by remember { mutableStateOf(mealTime) }
            MealTimeTabs(
                selectedMeal = selectedMeal,
                onMealSelected = { meal ->
                    selectedMeal = meal
                }
            )

            val menus = meal.menusByMealType[selectedMeal]
            if (!menus.isNullOrEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .height(480.dp)
                        .padding(start = 30.dp, top = 5.dp, end = 30.dp)
                ) {
                    items( menus.groupBy { it.course }.toList()) { (course, courseMenus) ->
                        CourseCard(course, courseMenus)
                    }
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .height(480.dp)
                        .padding(start = 30.dp, top = 5.dp, end = 30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("메뉴가 없습니다.")
                }
            }

            CurrentOperatingHourText(selectedMeal)
        }
    }
}

@Composable
fun MealTimeTabs(
    selectedMeal: MealType,
    onMealSelected: (MealType) -> Unit
) {
    val items = listOf(
        Triple(MealType.BREAKFAST, "아침", R.drawable.ic_morning),
        Triple(MealType.LUNCH, "점심", R.drawable.ic_lunch),
        Triple(MealType.DINNER, "저녁", R.drawable.ic_dinner)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp, vertical = 10.dp)
    ) {
        items.forEach { (mealType, title, iconRes) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() }, // ripple 효과 제거
                        indication = null
                    ) { onMealSelected(mealType) }
            ) {
                // 선택된 탭이면 아이콘 표시
                if (mealType == selectedMeal) {
                    Icon(
                        painter = painterResource(id = iconRes), // 원하는 아이콘
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 4.dp)
                    )
                }

                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontWeight = if (mealType == selectedMeal) FontWeight.Bold else FontWeight.Normal,
                    color = if (mealType == selectedMeal) Color.Black else Color.Gray
                )
            }
        }
    }
}

@Composable
fun CurrentOperatingHourText(mealTime: MealType) {
    val openHour = when (mealTime) {
        MealType.BREAKFAST -> "평일 07:30~09:00 테이크아웃 08:30 - 09:30\n" + "주말/공휴일 8:00~9:00"
        MealType.LUNCH -> "평일 11:30~13:00\n" +
                "주말/공휴일 12:00~13:30"
        MealType.DINNER -> "평일 17:30~19:00\n" + "주말/공휴일 17:30~18:40"
    }

    Text(
        text = openHour,
        color = Gray04,
        fontSize = 13.sp,
        modifier = Modifier
            .padding(start = 34.dp, top = 15.dp, bottom = 20.dp),
        lineHeight = 20.sp
    )
}

@Composable
fun CourseCard(course: String, menus: List<Menu>) {
    Column(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .width(163.dp)
                .height(230.dp)
                .border(
                    width = 1.dp,
                    color = LightYellow,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 21.dp)
            ) {
                CourseTitle(course, DarkYellow)
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 2.dp)
                ) {
                    menus.forEach { menu ->
                        Text(
                            text = menu.menuName,
                            fontSize = 14.sp,
                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun CourseTitle(course: String, textColor: Color) {
    val parts = course.split("/")

    val courseName = parts.getOrNull(0) ?: ""   // "B코스"
    val courseType = parts.getOrNull(1) ?: ""   // "베이커리"
    Row(
        modifier = Modifier
            .padding(bottom = 11.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = courseName,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = courseType,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}