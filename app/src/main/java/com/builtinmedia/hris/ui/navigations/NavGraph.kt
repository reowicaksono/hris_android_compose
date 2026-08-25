package com.builtinmedia.hris.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.builtinmedia.hris.features.auth.presentation.screens.LoginScreen
import com.builtinmedia.hris.features.presence.presentation.screens.PresenceScreen
import com.builtinmedia.hris.features.splash.presentation.screens.SplashScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Presence.route) {
            PresenceScreen(navController = navController)
        }
    }
}