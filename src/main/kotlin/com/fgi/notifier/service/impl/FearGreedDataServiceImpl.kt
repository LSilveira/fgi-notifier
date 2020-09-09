package com.fgi.notifier.service.impl

import com.fgi.notifier.component.FGIYamlComponent
import com.fgi.notifier.component.WebCrawler
import com.fgi.notifier.config.NotifierConfig
import com.fgi.notifier.model.EmailMessage
import com.fgi.notifier.model.EmailTemplate
import com.fgi.notifier.model.FGIInfo
import com.fgi.notifier.model.FearGreedData
import com.fgi.notifier.service.FearGreedDataService
import com.fgi.notifier.util.CrawlerConstants
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FearGreedDataServiceImpl : FearGreedDataService
{
    private val logger: Logger = LoggerFactory.getLogger(FearGreedDataServiceImpl::class.java)

    @Autowired
    private lateinit var webCrawler: WebCrawler

    @Autowired
    private lateinit var notifierConfig: NotifierConfig

    @Autowired
    private lateinit var fGIYamlComponent: FGIYamlComponent

    override fun getLatestData() : FearGreedData
    {
        return webCrawler.crawl(CrawlerConstants.FEAR_GREED_URL, FearGreedData()) as FearGreedData
    }

    override fun evaluateAndGetMessages(): List<EmailMessage>
    {
        val previousFearGreedData = fGIYamlComponent.readConfig()
        val latestFearGreedData = getLatestData()
        fGIYamlComponent.writeConfig(latestFearGreedData)

        if (previousFearGreedData == null || latestFearGreedData == previousFearGreedData)
        {
            logger.info("No updates!!")
            return emptyList()
        }

        val templateType = EmailTemplate.DEFAULT

        logger.info("FGI updated!! Email being sent!")
        return notifierConfig.getEmails().map { EmailMessage(it, "Fear & Greed Index change",
                templateType, latestFearGreedData.now!!, latestFearGreedData.nowPercentage!!,
                previousFearGreedData.now!!, previousFearGreedData.nowPercentage!!) }
    }

    override fun getFGIInfo(): FGIInfo
    {
        val previousFearGreedData = fGIYamlComponent.readConfig()
        val latestFearGreedData = getLatestData()

        return FGIInfo(
                previousFearGreedData?.now?:"",
                previousFearGreedData?.nowPercentage?:0,
                latestFearGreedData.now?:"",
                latestFearGreedData.nowPercentage?:0
        )
    }
}