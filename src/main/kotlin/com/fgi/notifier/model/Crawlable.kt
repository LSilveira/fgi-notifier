package com.fgi.notifier.model

import org.jsoup.nodes.Document

interface Crawlable
{
    fun crawl(doc: Document)
}