package com.hariom.pregnancy.notification

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NotificationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NotificationRepository(application)

    private val _isReminderEnabled = MutableLiveData<Boolean>()
    val isReminderEnabled: LiveData<Boolean> = _isReminderEnabled

    init {
        checkReminderStatus()
    }

    fun scheduleReminder() {
        repository.scheduleVitalsReminder()
        _isReminderEnabled.value = true
    }

    fun cancelReminder() {
        repository.cancelVitalsReminder()
        _isReminderEnabled.value = false
    }

    fun checkReminderStatus() {
        _isReminderEnabled.value = repository.isReminderScheduled()
    }
}
