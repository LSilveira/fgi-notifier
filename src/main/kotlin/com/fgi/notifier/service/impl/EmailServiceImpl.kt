package com.fgi.notifier.service.impl

import com.fgi.notifier.model.EmailMessage
import com.fgi.notifier.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.ITemplateEngine
import org.thymeleaf.context.Context

@Service
class EmailServiceImpl : EmailService
{
    @Autowired
    private lateinit var emailSender: JavaMailSender

    @Autowired
    private lateinit var thymeleafTemplateEngine: ITemplateEngine

    override fun send(emailMessage: EmailMessage)
    {
        val thymeleafContext = Context()
        val templateModel = mapOf("email" to emailMessage.to, "currentLevel" to emailMessage.currentFGI,
                "currentValue" to emailMessage.currentFGIPercent, "previousLevel" to emailMessage.previousFGI,
                "previousValue" to emailMessage.previousFGIPercent)
        thymeleafContext.setVariables(templateModel)
        val htmlBody = thymeleafTemplateEngine.process(emailMessage.template.path, thymeleafContext)

        val message = emailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setTo(emailMessage.to)
        helper.setSubject(emailMessage.title)
        helper.setText(htmlBody, true)

        emailSender.send(message)
    }
}