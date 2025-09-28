package com.hajjcompanion.core.common.notify

import java.time.Instant

interface ReminderScheduler {
    fun schedule(id: String, at: Instant, title: String, body: String)
    fun cancel(id: String)
}
