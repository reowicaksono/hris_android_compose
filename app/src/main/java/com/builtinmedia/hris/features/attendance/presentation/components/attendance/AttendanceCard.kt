package com.builtinmedia.hris.features.attendance.presentation.components.attendance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.builtinmedia.hris.core.utils.Utils
import com.builtinmedia.hris.features.attendance.presentation.business.AttendanceState
import com.builtinmedia.hris.ui.theme.HrisColors
import com.builtinmedia.hris.ui.theme.LocalSpacing

@Composable
fun AttendanceCard(
    state: AttendanceState,
    onCheckIn: (Double, Double) -> Unit,
    onCheckOut: (Double, Double) -> Unit
) {
    val spacing = LocalSpacing.current

    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.space20 ?: 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(state.isCheckedIn){
                StatusBadge(
                    text = "Working",
                    bg = HrisColors.SuccessBg,
                    textColor= HrisColors.Success
                )
            }else{
                StatusBadge(
                    text = "Not Checked In",
                    bg = MaterialTheme.colorScheme.surfaceVariant,
                    textColor= MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.height(spacing.space12))

            Text(
                text = state.today?.jamMasuk?.takeLast(8)?.take(5)?.let { Utils.formatTime(it) }
                    ?: "--:--",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(spacing.space8))

            val statusLine = when {
                state.isCheckedIn -> "Checked In • ${state.workingDurationText.ifBlank { "0h 0m" }}"
                state.today?.jamPulang != null -> "Checked Out"
                else -> "Belum absen hari ini"
            }

            Text(
                statusLine,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if(state.today?.locationName != null || state.isCheckedIn){
                Spacer(Modifier.height(spacing.space4))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(spacing.space16)
                    )
                    Spacer(Modifier.size(spacing.space4))
                    Text(
                        state.today?.locationName ?: "Kantor",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(Modifier.size(spacing.space20))
                Button(
                    onClick = {
                        if (state.isCheckedIn) onCheckOut(0.0, 0.0) else onCheckIn(0.0, 0.0)
                    },
                    enabled = !state.isSubmitting,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    if (state.isSubmitting) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            if (state.isCheckedIn) "Check Out" else "Check In",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }
        }
    }
}