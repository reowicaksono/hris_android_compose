package com.builtinmedia.hris.features.attendance.presentation.components.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import com.builtinmedia.hris.features.attendance.presentation.business.AttendanceState

@Composable
fun AnnouncementsSection(state: AttendanceState) {
    when {
        state.isAnnouncementLoading -> {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(3) { AnnouncementCardSkeleton() }
            }
        }

        state.announcements.isEmpty() -> {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Belum ada pengumuman",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        else -> {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.announcements, key = { it.id ?: it.hashCode() }) { announcement ->
                    AnnouncementCard(announcement)
                }
            }
        }
    }
}