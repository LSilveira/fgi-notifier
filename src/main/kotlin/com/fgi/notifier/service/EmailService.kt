package com.fgi.notifier.service

import com.fgi.notifier.model.EmailMessage

interface EmailService
{
    fun send(emailMessage: EmailMessage)
}