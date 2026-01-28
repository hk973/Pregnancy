package com.hariom.pregnancy.View

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.hariom.pregnancy.Entries_model.AppDatabase
import com.hariom.pregnancy.Entries_model.EntriesRepository
import com.hariom.pregnancy.Entries_viewModel.EntriesViewModel
import com.hariom.pregnancy.View.UI_module.EntriesScreen
import com.hariom.pregnancy.notification.NotificationViewModel

class MainActivity : ComponentActivity() {

    private lateinit var notificationViewModel: NotificationViewModel
    private val shouldOpenLoggingScreen = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            notificationViewModel.scheduleReminder()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]

        handleIntent(intent)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "entries_db"
        ).build()

        val dao = db.entriesDao()
        val repository = EntriesRepository(dao)
        val viewModel = EntriesViewModel(repository)

        requestNotificationPermissionAndSchedule()

        setContent {
            EntriesScreen(
                viewModel = viewModel,
                shouldOpenLoggingScreen = shouldOpenLoggingScreen.value
            )
        }
    }

    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: android.content.Intent?) {
        shouldOpenLoggingScreen.value = intent?.getBooleanExtra("open_logging_screen", false) ?: false
    }

    private fun requestNotificationPermissionAndSchedule() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    notificationViewModel.scheduleReminder()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            notificationViewModel.scheduleReminder()
        }
    }
}
