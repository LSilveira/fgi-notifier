package com.fgi.notifier.service

import com.fgi.notifier.model.EmailMessage
import com.fgi.notifier.model.FGIInfo
import com.fgi.notifier.model.FearGreedData

interface FearGreedDataService
{
    /**
     * Get latest data from fear greed index
     */
    fun getLatestData() : FearGreedData

    /**
     * Evaluate fear greed index and get email messages
     */
    fun evaluateAndGetMessages(): List<EmailMessage>

    /**
     * Get fear greed index info
     */
    fun getFGIInfo(): FGIInfo
}