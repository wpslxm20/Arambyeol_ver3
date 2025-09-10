package com.arambyeol.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.arambyeol.ui.theme.Gray01
import com.arambyeol.ui.theme.Gray03
import com.arambyeol.ui.theme.Gray05

val orderedMealTypes = listOf(MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER)

@Composable
fun DailyMealCard(meals: State<Meal?>, mealTime: MealType) {
    meals.value?.let { meal ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            orderedMealTypes.forEach { type ->
                val isColor = type == mealTime
                meal.menusByMealType[type]?.let { menus ->
                    if (menus.isNotEmpty()) {
                        MealTimeTitle(type)
                        Row(
                            modifier = Modifier
                                .padding(bottom = 29.dp)
                                .horizontalScroll(rememberScrollState())
                        ) {
                            menus.groupBy { it.course }
                                .toList()
                                .forEachIndexed { index, (course, courseMenus) ->
                                CourseCard(course, courseMenus, isColor, index)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MealTimeTitle(mealTime: MealType) {
    val (title, iconRes) = when (mealTime) {
        MealType.BREAKFAST -> "아침" to R.drawable.ic_morning
        MealType.LUNCH -> "점심" to R.drawable.ic_lunch
        MealType.DINNER -> "저녁" to R.drawable.ic_dinner
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 14.dp)
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 7.dp)
        )
        Text(
            text = title,
            color = Gray01,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 3.dp)
        )
    }
}

@Composable
fun CourseCard(course: String, menus: List<Menu>, isColor: Boolean, index: Int) {
    val colorSet = listOf(DarkYellow, DarkGreen, DarkRed)
    val backgroundColor = if (isColor) colorSet[index%3].copy(alpha = (255f / 10) / 255f) else Gray05
    val textColor = if (isColor) colorSet[index%3] else Gray03
    Column(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .width(220.dp)
                .height(140.dp)
                .padding(end = 17.dp)
                .background(backgroundColor, shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                CourseTitle(course, textColor)
                Box(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = menus.joinToString(", ") { it.menuName },
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