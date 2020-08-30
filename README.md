# FGI-Notifier

## [Fear and Greed Index](https://money.cnn.com/investing/about-fear-greed-tool/index.html)
Based on investors are driven by two emotions: fear and greed. Too much fear can sink stocks well below where they should be. When investors get greedy, they can bid up stock prices way too far. [CNN Money](http://money.cnn.com) developed an index to measure people's emotions using 7 indicators.

* Stock Price Momentum: The S&P 500 (SPX) versus its 125-day moving average

* Stock Price Strength: The number of stocks hitting 52-week highs and lows on the New York Stock Exchange

* Stock Price Breadth: The volume of shares trading in stocks on the rise versus those declining.

* Put and Call Options: The put/call ratio, which compares the trading volume of bullish call options relative to the trading volume of bearish put options

* Junk Bond Demand: The spread between yields on investment grade bonds and junk bonds

* Market Volatility: The VIX (VIX), which measures volatility

* Safe Haven Demand: The difference in returns for stocks versus Treasuries

## Project Overview
FGI-Notifier is a web application that collects data from Fear Greed Index and notifies the users by email of emotion changes.

## Libraries/Frameworks Used
* Java 11/Kotlin 1.3.72
* Spring boot
* Lombok
* Jsoup
* Mockito-kotlin
* Thymeleaf
