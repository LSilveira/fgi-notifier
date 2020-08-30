package com.fgi.notifier.scheduler

class Task
(
        val action: () -> Unit
) : Runnable
{
    override fun run()
    {
        action()
    }
}