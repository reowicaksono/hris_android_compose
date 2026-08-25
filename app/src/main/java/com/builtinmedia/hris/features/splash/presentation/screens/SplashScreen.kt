package com.builtinmedia.hris.features.splash.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.builtinmedia.hris.features.splash.presentation.business.SplashUiEvent
import com.builtinmedia.hris.features.splash.presentation.business.SplashViewModel
import com.builtinmedia.hris.ui.navigations.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        splashViewModel.uiEvent.collectLatest { event ->
            val destination = when (event) {
                SplashUiEvent.NavigateToLogin -> Screen.Login.route
                SplashUiEvent.NavigateToPresence -> Screen.Presence.route
            }

            navController.navigate(destination){
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "HRIS",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}