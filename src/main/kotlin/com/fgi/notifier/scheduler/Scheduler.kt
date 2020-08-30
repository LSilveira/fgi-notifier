package com.fgi.notifier.scheduler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Component

@Component
class Scheduler
{
    private val logger: Logger = LoggerFactory.getLogger(Scheduler::class.java)

    @Autowired
    private lateinit var executor: TaskScheduler

    fun runSchedule(task: Task, cronExpressions: List<String>)
    {
        cronExpressions.forEach { executor.schedule(task, CronTrigger(it)) }
    }
}