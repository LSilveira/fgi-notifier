package com.fgi.notifier.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "notifier")
class NotifierConfig
{
    private lateinit var emails: List<String>
    private lateinit var cron: List<String>

    fun setEmails(emails: List<String>)
    {
        this.emails = emails
    }

    fun getEmails() : List<String>
    {
        return emails
    }

    fun setCron(cron: List<String>)
    {
        this.cron = cron
    }

    fun getCron() : List<String>
    {
        return cron
    }
}