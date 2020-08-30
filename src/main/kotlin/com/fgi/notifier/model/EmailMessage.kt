package com.fgi.notifier.model

data class EmailMessage
(
        val to: String,
        val title: String,
        val template: EmailTemplate,
        val currentFGI: String,
        val currentFGIPercent: Int,
        val previousFGI: String,
        val previousFGIPercent: Int
)