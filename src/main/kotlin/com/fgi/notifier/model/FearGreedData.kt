package com.fgi.notifier.model

import com.fgi.notifier.util.CrawlerConstants
import com.fgi.notifier.util.PatternConstants
import org.jsoup.nodes.Document

class FearGreedData : Crawlable
{
    var now: String? = ""
    var nowPercentage: Int? = null
    var prev: String? = ""
    var prevPercentage: Int? = null
    var week: String? = ""
    var weekPercentage: Int? = null
    var month: String? = ""
    var monthPercentage: Int? = null
    var year: String? = ""
    var yearPercentage: Int? = null

    override fun crawl(doc: Document)
    {
        val fearGreedData = doc.select(CrawlerConstants.FEAR_GREED_DATA).text()

        // create regex matchers
        val matcherNow = PatternConstants.NOW.matcher(fearGreedData)
        val matcherPrevious = PatternConstants.PREVIOUS_CLOSE.matcher(fearGreedData)
        val matcherWeekAgo = PatternConstants.WEEK_AGO.matcher(fearGreedData)
        val matcherMonthAgo = PatternConstants.MONTH_AGO.matcher(fearGreedData)
        val matcherYearAgo = PatternConstants.YEAR_AGO.matcher(fearGreedData)

        // execute matchers
        matcherNow.find()
        matcherPrevious.find()
        matcherWeekAgo.find()
        matcherMonthAgo.find()
        matcherYearAgo.find()

        // map matchers data into a pair containing
        // fear greed index percentage and description
        // for different timeframes
        val nowPair = mapData(matcherNow.group().toString())
        val previousPair = mapData(matcherPrevious.group().toString())
        val weekPair = mapData(matcherWeekAgo.group().toString())
        val monthPair = mapData(matcherMonthAgo.group().toString())
        val yearPair = mapData(matcherYearAgo.group().toString())

        // fill in fear greed index data
        now = nowPair.second
        nowPercentage = nowPair.first

        prev = previousPair.second
        prevPercentage = previousPair.first

        week = weekPair.second
        weekPercentage = weekPair.first

        month = monthPair.second
        monthPercentage = monthPair.first

        year = yearPair.second
        yearPercentage = yearPair.first
    }

    /**
     * Map a string with fear greed index percentage and description into
     * a Pair
     */
    private fun mapData(data: String) : Pair<Int,String>
    {
        val percentageMatcher = PatternConstants.DIGITS.matcher(data)
        val descriptionMatcher = PatternConstants.WORD.matcher(data)

        percentageMatcher.find()
        descriptionMatcher.find()

        val percentage = percentageMatcher.group().toInt()
        val description = descriptionMatcher.group().toString()
                .replace(")", "")

        return Pair(percentage, description)
    }

    override fun equals(other: Any?): Boolean
    {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FearGreedData

        if (now != other.now) return false
        if (nowPercentage != other.nowPercentage) return false
        if (prev != other.prev) return false
        if (prevPercentage != other.prevPercentage) return false
        if (week != other.week) return false
        if (weekPercentage != other.weekPercentage) return false
        if (month != other.month) return false
        if (monthPercentage != other.monthPercentage) return false
        if (year != other.year) return false
        if (yearPercentage != other.yearPercentage) return false

        return true
    }

    override fun hashCode(): Int
    {
        var result = now?.hashCode() ?: 0
        result = 31 * result + (nowPercentage ?: 0)
        result = 31 * result + (prev?.hashCode() ?: 0)
        result = 31 * result + (prevPercentage ?: 0)
        result = 31 * result + (week?.hashCode() ?: 0)
        result = 31 * result + (weekPercentage ?: 0)
        result = 31 * result + (month?.hashCode() ?: 0)
        result = 31 * result + (monthPercentage ?: 0)
        result = 31 * result + (year?.hashCode() ?: 0)
        result = 31 * result + (yearPercentage ?: 0)
        return result
    }
}