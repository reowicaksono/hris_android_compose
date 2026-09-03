package com.builtinmedia.hris.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.builtinmedia.hris.ui.navigations.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        Screen.Attendance,
        Screen.LeaveRequest,
        Screen.Payslip,
        Screen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        Screen.Attendance -> Icon(Icons.Default.Fingerprint, contentDescription = "Home")
                        Screen.LeaveRequest -> Icon(Icons.Default.ShoppingCart, contentDescription = "LeaveRequest")
                        Screen.Payslip -> Icon(Icons.Default.Person, contentDescription = "Payslip")
                        Screen.Profile -> Icon(Icons.Default.Person, contentDescription = "Profile")
                        else -> {}
                    }
                },
                label = {
                    when (screen) {
                        Screen.Attendance -> Text("Attendance")
                        Screen.LeaveRequest -> Text("LeaveRequest")
                        Screen.Payslip -> Text("Payslip")
                        Screen.Profile -> Text("Profile")
                        else -> {}
                    }
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Screen.Attendance.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

