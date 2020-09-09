package com.fgi.notifier.endpoint.info

import com.fgi.notifier.service.FearGreedDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.stereotype.Component
import java.util.*


@Component
class FGIInfoContributor : InfoContributor
{
    @Autowired
    private lateinit var fearGreedDataService: FearGreedDataService

    override fun contribute(builder: Info.Builder?)
    {
        builder?.withDetail("fgiupdated", fearGreedDataService.getFGIInfo())
    }
}