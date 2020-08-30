package com.fgi.notifier.service.impl

import com.fgi.notifier.config.NotifierConfig
import com.fgi.notifier.scheduler.Scheduler
import com.fgi.notifier.scheduler.Task
import com.fgi.notifier.service.EmailService
import com.fgi.notifier.service.FearGreedDataService
import com.fgi.notifier.service.SchedulerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class SchedulerServiceImpl : SchedulerService
{
    @Autowired
    private lateinit var emailService: EmailService

    @Autowired
    private lateinit var  fearGreedDataService: FearGreedDataService

    @Autowired
    private lateinit var scheduler: Scheduler

    @Autowired
    private lateinit var notifierConfig: NotifierConfig

    override fun scheduleMessages()
    {
        val task = Task{
            fearGreedDataService.evaluateAndGetMessages().forEach(Consumer { emailService.send(it) })
        }

        scheduler.runSchedule(task, notifierConfig.getCron())
    }
}