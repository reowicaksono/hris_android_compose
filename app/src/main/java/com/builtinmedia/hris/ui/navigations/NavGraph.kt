package com.builtinmedia.hris.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.builtinmedia.hris.features.auth.presentation.screens.LoginScreen
import com.builtinmedia.hris.features.home.presentation.screens.HomeScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}