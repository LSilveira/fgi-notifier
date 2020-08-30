package com.fgi.notifier.service.impl

import com.fgi.notifier.component.FGIYamlComponent
import com.fgi.notifier.component.WebCrawler
import com.fgi.notifier.config.NotifierConfig
import com.fgi.notifier.model.FearGreedData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.util.ReflectionTestUtils

@ExtendWith(MockitoExtension::class)
internal class FearGreedDataServiceTest
{
    private val NOW = "Extreme Fear"
    private val NOW_PERCENTAGE = 10
    private val PREV = "Fear"
    private val PREV_PERCENTAGE = 30
    private val WEEK = "Neutral"
    private val WEEK_PERCENTAGE = 50
    private val MONTH = "Greed"
    private val MONTH_PERCENTAGE = 70
    private val YEAR = "Extreme Greed"
    private val YEAR_PERCENTAGE = 90

    @InjectMocks
    private lateinit var  fearGreedDataService: FearGreedDataServiceImpl

    @Mock
    private lateinit var webCrawler: WebCrawler

    @Mock
    private lateinit var fGIYamlComponent: FGIYamlComponent

    @Mock
    private lateinit var notifierConfig: NotifierConfig

    @BeforeEach
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)

        val fearGreedData = FearGreedData()
        ReflectionTestUtils.setField(fearGreedData, "now", NOW)
        ReflectionTestUtils.setField(fearGreedData, "nowPercentage", NOW_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "prev", PREV)
        ReflectionTestUtils.setField(fearGreedData, "prevPercentage", PREV_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "week", WEEK)
        ReflectionTestUtils.setField(fearGreedData, "weekPercentage", WEEK_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "month", MONTH)
        ReflectionTestUtils.setField(fearGreedData, "monthPercentage", MONTH_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "year", YEAR)
        ReflectionTestUtils.setField(fearGreedData, "yearPercentage", YEAR_PERCENTAGE)

        whenever(webCrawler.crawl(anyString(), any<FearGreedData>()) as FearGreedData?)
                .thenReturn(fearGreedData)
    }

    @Test
    fun getLatestData()
    {
        val fearGreedData = fearGreedDataService.getLatestData()

        Assertions.assertNotNull(fearGreedData)
        Assertions.assertTrue(fearGreedData.now == NOW)
        Assertions.assertTrue(fearGreedData.nowPercentage == NOW_PERCENTAGE)
        Assertions.assertTrue(fearGreedData.prev == PREV)
        Assertions.assertTrue(fearGreedData.prevPercentage == PREV_PERCENTAGE)
        Assertions.assertTrue(fearGreedData.week == WEEK)
        Assertions.assertTrue(fearGreedData.weekPercentage == WEEK_PERCENTAGE)
        Assertions.assertTrue(fearGreedData.month == MONTH)
        Assertions.assertTrue(fearGreedData.monthPercentage == MONTH_PERCENTAGE)
        Assertions.assertTrue(fearGreedData.year == YEAR)
        Assertions.assertTrue(fearGreedData.yearPercentage == YEAR_PERCENTAGE)
    }

    @Test
    fun evaluateNoNewMessages()
    {
        val fearGreedData = FearGreedData()
        ReflectionTestUtils.setField(fearGreedData, "now", NOW)
        ReflectionTestUtils.setField(fearGreedData, "nowPercentage", NOW_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "prev", PREV)
        ReflectionTestUtils.setField(fearGreedData, "prevPercentage", PREV_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "week", WEEK)
        ReflectionTestUtils.setField(fearGreedData, "weekPercentage", WEEK_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "month", MONTH)
        ReflectionTestUtils.setField(fearGreedData, "monthPercentage", MONTH_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "year", YEAR)
        ReflectionTestUtils.setField(fearGreedData, "yearPercentage", YEAR_PERCENTAGE)

        whenever(fGIYamlComponent.readConfig())
                .thenReturn(fearGreedData)

        val messages = fearGreedDataService.evaluateAndGetMessages()

        Assertions.assertNotNull(messages)
        Assertions.assertTrue(messages.isEmpty())
    }

    @Test
    fun evaluateNewMessages()
    {
        val emailsList = listOf("test@test.com", "test2@test.com")

        whenever(notifierConfig.getEmails())
                .thenReturn(emailsList)

        val fearGreedData = FearGreedData()
        ReflectionTestUtils.setField(fearGreedData, "now", "Fear")
        ReflectionTestUtils.setField(fearGreedData, "nowPercentage", 25)
        ReflectionTestUtils.setField(fearGreedData, "prev", PREV)
        ReflectionTestUtils.setField(fearGreedData, "prevPercentage", PREV_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "week", WEEK)
        ReflectionTestUtils.setField(fearGreedData, "weekPercentage", WEEK_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "month", MONTH)
        ReflectionTestUtils.setField(fearGreedData, "monthPercentage", MONTH_PERCENTAGE)
        ReflectionTestUtils.setField(fearGreedData, "year", YEAR)
        ReflectionTestUtils.setField(fearGreedData, "yearPercentage", YEAR_PERCENTAGE)

        whenever(fGIYamlComponent.readConfig())
                .thenReturn(fearGreedData)

        val messages = fearGreedDataService.evaluateAndGetMessages()

        Assertions.assertNotNull(messages)
        Assertions.assertTrue(messages.isNotEmpty())
    }
}