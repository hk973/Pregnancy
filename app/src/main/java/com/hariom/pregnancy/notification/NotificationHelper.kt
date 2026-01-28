package com.hariom.pregnancy.notification

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

object NotificationHelper {
    
    fun triggerTestNotification(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<VitalsReminderWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
    
    fun areRemindersEnabled(context: Context): Boolean {
        val workManager = WorkManager.getInstance(context)
        val workInfos = workManager.getWorkInfosForUniqueWork(VitalsReminderWorker.WORK_NAME).get()
        return workInfos.any { !it.state.isFinished }
    }
}
