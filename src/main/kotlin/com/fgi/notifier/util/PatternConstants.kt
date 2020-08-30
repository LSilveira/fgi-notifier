package com.fgi.notifier.util

import java.util.regex.Pattern

class PatternConstants
{
    companion object
    {
        val NOW = Pattern.compile("Now: \\d+ \\(\\w+( \\w+)?\\)")
        val PREVIOUS_CLOSE = Pattern.compile("Close: \\d+ \\(\\w+(\\s\\w+)?\\)")
        val WEEK_AGO = Pattern.compile("Week Ago: \\d+ \\(\\w+(\\s\\w+)?\\)")
        val MONTH_AGO = Pattern.compile("Month Ago: \\d+ \\(\\w+(\\s\\w+)?\\)")
        val YEAR_AGO = Pattern.compile("Year Ago: \\d+ \\(\\w+(\\s\\w+)?\\)")
        val DIGITS = Pattern.compile("\\d+")
        val WORD = Pattern.compile("\\w+(\\s\\w+)?\\)$")
    }
}