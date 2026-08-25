package com.builtinmedia.hris.features.presence.presentation.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.builtinmedia.hris.features.presence.presentation.business.PresenceUiEvent
import com.builtinmedia.hris.features.presence.presentation.business.PresenceViewModel
import com.builtinmedia.hris.ui.navigations.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PresenceScreen(
    navController: NavController,
    presenceViewModel: PresenceViewModel = hiltViewModel()
) {
    val state by presenceViewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        presenceViewModel.uiEvent.collectLatest { event ->
            when (event) {
                PresenceUiEvent.NavigateToLogin -> {
                    navController.navigate(Screen.Login.route){
                        popUpTo(0) { inclusive = true }
                    }
                }
                is PresenceUiEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Row(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        ){
            Text(
                text = "Presence",
                modifier = Modifier.padding(innerPadding),
                style = MaterialTheme.typography.headlineMedium
            )

            IconButton(
                onClick = {presenceViewModel.onLogoutClick()},
                enabled = !state.isLoggingOut
            ) {
                if (state.isLoggingOut){
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                }else {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout"
                    )
                }
            }
        }
    }
}