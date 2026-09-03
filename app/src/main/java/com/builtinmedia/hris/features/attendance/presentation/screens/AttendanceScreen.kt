package com.builtinmedia.hris.features.attendance.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.builtinmedia.hris.features.attendance.presentation.business.AttendanceEvent
import com.builtinmedia.hris.features.attendance.presentation.business.AttendanceUiEvent
import com.builtinmedia.hris.features.attendance.presentation.business.AttendanceViewModel
import com.builtinmedia.hris.features.attendance.presentation.components.attendance.AnnouncementsSection
import com.builtinmedia.hris.features.attendance.presentation.components.attendance.AttendanceCard
import com.builtinmedia.hris.features.attendance.presentation.components.attendance.AttendanceCardSkeleton
import com.builtinmedia.hris.ui.theme.LocalSpacing
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(
    onOpenHistory: () -> Unit,
    onOpenNotification: () -> Unit,
    viewModel: AttendanceViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val spacing = LocalSpacing.current

    LaunchedEffect(Unit) {
        viewModel.onEvent(AttendanceEvent.LoadData)
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when(event){
                is AttendanceUiEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    //Component
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text("Absensi", style = MaterialTheme.typography.titleLarge)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    IconButton(onClick = onOpenHistory) {
                        Icon(
                            Icons.Outlined.History,
                            contentDescription = "History Attendance",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(onClick = onOpenNotification) {
                        BadgedBox(badge = { Badge(containerColor = MaterialTheme.colorScheme.error) }) {
                            Icon(
                                Icons.Filled.Notifications,
                                contentDescription = "Notifikasi",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = spacing.space16)
        ) {
            Spacer(Modifier.height(spacing.space8))

            Text(
                text = LocalDate.now().format(
                    DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale("en"))
                ),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(spacing.space12))

            if(state.isLoading){
                AttendanceCardSkeleton()
            }else{
                AttendanceCard(
                    state = state,
                    onCheckIn = {lat,long -> viewModel.onEvent(AttendanceEvent.CheckIn(lat,long))},
                    onCheckOut = {lat,long -> viewModel.onEvent(AttendanceEvent.CheckOut(lat,long))}
                )
            }

            Spacer(Modifier.height(spacing.space12))

            Text(
                text = "Pengumuman Terbaru",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(spacing.space12))

            AnnouncementsSection(state = state)
        }
    }
}