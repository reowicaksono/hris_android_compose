package com.builtinmedia.hris.features.attendance.presentation.components.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.builtinmedia.hris.ui.components.Skeleton.SkeletonBox

@Composable
fun AnnouncementCardSkeleton() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SkeletonBox(modifier = Modifier.fillMaxWidth(0.6f).height(16.dp))
                SkeletonBox(modifier = Modifier.width(60.dp).height(18.dp), shape = RoundedCornerShape(50))
            }
            Spacer(Modifier.height(10.dp))
            SkeletonBox(modifier = Modifier.fillMaxWidth().height(12.dp))
            Spacer(Modifier.height(6.dp))
            SkeletonBox(modifier = Modifier.fillMaxWidth(0.8f).height(12.dp))
            Spacer(Modifier.height(10.dp))
            SkeletonBox(modifier = Modifier.width(80.dp).height(10.dp))
        }
    }
}