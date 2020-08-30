package com.fgi.notifier.component

import com.fgi.notifier.model.Crawlable
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component


@Component
class WebCrawler
{
    fun crawl(url: String, crawlable: Crawlable) : Crawlable
    {
        val doc: Document = Jsoup.connect(url).get()
        crawlable.crawl(doc)

        return crawlable
    }
}