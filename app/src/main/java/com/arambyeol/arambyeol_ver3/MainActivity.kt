package com.arambyeol.arambyeol_ver3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.arambyeol.arambyeol_ver3.ui.theme.Arambyeol_ver3Theme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.arambyeol.todaymeal.TodayMealScreen
import com.arambyeol.ui.component.UnderConstructionMessage
import com.arambyeol.weeklymeal.WeeklyMealScreen

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
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHostWithTopTabBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AppHeader()
            TopTabBar(
                tabs = topTabs,
                selectedIndex = selectedIndex,
                onTapSelected = { selectedIndex = it }
            )
        }

        when (selectedIndex) {
            0 -> TodayMealScreen()
            1 -> WeeklyMealScreen()
            2, 3, 4 -> UnderConstructionMessage()
        }
    }
}



@Preview(name = "Small Phone", widthDp = 320, heightDp = 640, showBackground = true)
@Preview(name = "Normal Phone", widthDp = 360, heightDp = 800, showBackground = true)
@Preview(name = "Large Phone", widthDp = 411, heightDp = 900, showBackground = true)
@Composable
fun AppNavHostWithTopTabBarPreview() {
    Arambyeol_ver3Theme {
        AppNavHostWithTopTabBar()
    }
}
