package com.hajjcompanion.core.common.analytics

interface Analytics {
    fun logEvent(name: String, params: Map<String, Any?> = emptyMap())
}
