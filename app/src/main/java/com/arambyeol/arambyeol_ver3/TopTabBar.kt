package com.arambyeol.arambyeol_ver3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arambyeol.arambyeol_ver3.ui.theme.DarkYellow
import com.arambyeol.arambyeol_ver3.ui.theme.Gray01

@Composable
fun TopTabBar(
    tabs: List<TopTabItem>,
    selectedIndex: Int,
    onTapSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 13.dp, end = 12.dp, bottom = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEachIndexed { index, tab ->
            val isSelected = selectedIndex == index
            Text(
                text = tab.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else Gray01,
                modifier = Modifier
                    .background(
                        color = if (isSelected) DarkYellow else Color.Transparent,
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 12.dp, vertical = 3.dp)
                    .clickable { onTapSelected(index) }
            )
        }
    }
}
