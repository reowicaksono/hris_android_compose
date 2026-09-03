package com.builtinmedia.hris.features.attendance.presentation.components.attendance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.builtinmedia.hris.ui.components.Skeleton.SkeletonBox

@Composable
fun AttendanceCardSkeleton() {
    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SkeletonBox(
                modifier = Modifier.width(90.dp).height(24.dp),
                shape = RoundedCornerShape(50)
            )
            Spacer(Modifier.height(16.dp))
            SkeletonBox(modifier = Modifier.width(160.dp).height(42.dp))
            Spacer(Modifier.height(12.dp))
            SkeletonBox(modifier = Modifier.width(180.dp).height(16.dp))
            Spacer(Modifier.height(8.dp))
            SkeletonBox(modifier = Modifier.width(120.dp).height(14.dp))
            Spacer(Modifier.height(20.dp))
            SkeletonBox(
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp)
            )
        }
    }
}