package com.builtinmedia.hris.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.builtinmedia.hris.features.attendance.presentation.screens.AttendanceScreen
import com.builtinmedia.hris.features.auth.presentation.screens.LoginScreen
import com.builtinmedia.hris.features.splash.presentation.screens.SplashScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateLogin = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateAttendance = {
                    navController.navigate(Screen.Attendance.route){
                        popUpTo(Screen.Attendance.route) { inclusive = true }
                    }
                },
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Attendance.route){
                        popUpTo(Screen.Attendance.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Attendance.route) {
            AttendanceScreen(
                onOpenHistory = {},
                onOpenNotification = {}
            )
        }
    }
}