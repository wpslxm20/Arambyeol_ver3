package com.arambyeol.arambyeol_ver3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.arambyeol.arambyeol_ver3.ui.theme.Arambyeol_ver3Theme
import com.arambyeol.arambyeol_ver3.ui.theme.TransparentYellow
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Arambyeol_ver3Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.systemBars, // system bar inset 적용
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        AppNavHostWithTopTabBar()
//                      TodayMealScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHostWithTopTabBar() {
    val navController = rememberNavController()
    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = TransparentYellow),
        ) {
            AppHeader()
            TopTabBar(
                tabs = topTabs,
                selectedIndex = selectedIndex,
                onTapSelected = { index ->
                    selectedIndex = index
                    val route = topTabs[index].route
                    if (navController.currentDestination?.route != route) {
                        navController.navigate(route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId)
                        }
                    }
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp) // 그림자 높이
                .align(Alignment.CenterHorizontally)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.04f), // 위쪽 진한 그림자
                            Color.Transparent                // 아래쪽 투명
                        )
                    )
                )
        )

        // 실제 화면 NavHost
        AppNavHost(navController)
    }
}
