package com.hariom.pregnancy.View.UI_module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hariom.pregnancy.Entries_viewModel.EntriesViewModel
import com.hariom.pregnancy.ui.theme.ButtonPurple
import com.hariom.pregnancy.ui.theme.LightPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntriesScreen(
    viewModel: EntriesViewModel,
    shouldOpenLoggingScreen: Boolean = false
) {

    val vitals by viewModel.vitalsList.collectAsState()
    var showDialog by remember { mutableStateOf(shouldOpenLoggingScreen) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Track My Pregnancy",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF6B4C7A)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightPurple
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = ButtonPurple,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Vitals")
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                items(vitals) { entry ->
                    EntriesItem(entry)
                }
            }
        }

        if (showDialog) {
            AddEntriesDialog(
                onDismiss = { showDialog = false },
                onSubmit = { sys, dia, hr, wt, kicks ->
                    viewModel.addVitals(sys, dia, hr, wt, kicks)
                    showDialog = false
                }
            )
        }
    }
}