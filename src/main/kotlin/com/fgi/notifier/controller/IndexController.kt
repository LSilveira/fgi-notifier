package com.fgi.notifier.controller

import com.fgi.notifier.model.EmailMessage
import com.fgi.notifier.model.EmailTemplate
import com.fgi.notifier.service.EmailService
import com.fgi.notifier.service.FearGreedDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping
class IndexController
{
    @Autowired
    private lateinit var fearGreedDataService: FearGreedDataService

    @Autowired
    private lateinit var emailService: EmailService

    @GetMapping("/")
    fun index() : ModelAndView
    {
        val model = ModelAndView("index")

        model.addObject("fearGreedData", fearGreedDataService.getLatestData())

        return model
    }
}