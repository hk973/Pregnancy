package com.hariom.pregnancy.notification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class NotificationRepository(private val context: Context) {

    private val workManager = WorkManager.getInstance(context)

    fun scheduleVitalsReminder() {
        val workRequest = PeriodicWorkRequestBuilder<VitalsReminderWorker>(
           5 , TimeUnit.HOURS
        ).build()

        workManager.enqueueUniquePeriodicWork(
            VitalsReminderWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    fun cancelVitalsReminder() {
        workManager.cancelUniqueWork(VitalsReminderWorker.WORK_NAME)
    }

    fun isReminderScheduled(): Boolean {
        val workInfos = workManager.getWorkInfosForUniqueWork(VitalsReminderWorker.WORK_NAME).get()
        return workInfos.any { !it.state.isFinished }
    }
}
