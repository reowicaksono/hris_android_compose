package com.builtinmedia.hris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.builtinmedia.hris.ui.components.BottomNavigationBar
import com.builtinmedia.hris.ui.navigations.NavGraph
import com.builtinmedia.hris.ui.navigations.Screen
import com.builtinmedia.hris.ui.theme.HrisTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HrisTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

               val showBottomBar =
                   currentRoute in listOf(
                       Screen.Attendance.route,
                       Screen.LeaveRequest.route,
                       Screen.Payslip.route,
                       Screen.Profile.route
                   )
                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(navController)
                        }
                    },
                    modifier = Modifier.fillMaxSize()) { padding ->
                    Box(modifier = Modifier.padding(padding)){
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
