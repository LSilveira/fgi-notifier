package com.fgi.notifier.listener

import com.fgi.notifier.service.SchedulerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class ApplicationReadyListener : ApplicationListener<ApplicationReadyEvent>
{
    private val logger: Logger = LoggerFactory.getLogger(ApplicationReadyListener::class.java)

    @Autowired
    private lateinit var schedulerService: SchedulerService

    override fun onApplicationEvent(event: ApplicationReadyEvent)
    {
        logger.info("Started loading components...")

        schedulerService.scheduleMessages()

        logger.info("Finished loading components.")
    }
}