package com.hajjcompanion.core.common.media

interface OfflineMediaManager {
    suspend fun download(url: String): Result<String> // returns local path
    suspend fun isDownloaded(url: String): Boolean
    suspend fun remove(url: String)
}
