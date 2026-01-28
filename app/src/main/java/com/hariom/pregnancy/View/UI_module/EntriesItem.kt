package com.hariom.pregnancy.View.UI_module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hariom.pregnancy.Entries_model.Entries
import com.hariom.pregnancy.ui.theme.ButtonPurple
import com.hariom.pregnancy.ui.theme.CardPurple
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EntriesItem(entry: Entries) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Data section with light purple background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardPurple)
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VitalItem(
                            icon = "❤",
                            value = "${entry.heartRate} bpm"
                        )
                        VitalItem(
                            icon = "🩺",
                            value = "${entry.systolic}/${entry.diastolic} mmHg"
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VitalItem(
                            icon = "⚖",
                            value = "${entry.weight} kg"
                        )
                        VitalItem(
                            icon = "👶",
                            value = "${entry.babyKicks} kicks"
                        )
                    }
                }
            }
            
            // Date section with purple background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ButtonPurple)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = formatTimestamp(entry.timestamp),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun VitalItem(icon: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = icon,
            fontSize = 20.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4A4A4A)
        )
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.ENGLISH)
    return sdf.format(Date(timestamp))
}

