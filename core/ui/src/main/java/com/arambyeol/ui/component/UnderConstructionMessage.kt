package com.arambyeol.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun UnderConstructionMessage() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "\uD83D\uDC77\u200D♂\uFE0F준비 중인 서비스입니다.",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        Text(
            text = "더 나은 서비스로 찾아 뵙겠습니다.",
            fontSize = 14.sp
        )
    }
}