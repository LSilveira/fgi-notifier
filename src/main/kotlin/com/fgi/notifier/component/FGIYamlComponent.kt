package com.fgi.notifier.component

import com.fasterxml.jackson.databind.ObjectMapper
import com.fgi.notifier.model.FearGreedData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File

@Component
class FGIYamlComponent
{
    private val logger: Logger = LoggerFactory.getLogger(FGIYamlComponent::class.java)

    private val fGIFile = "fearGreedIndex.yaml"

    @Autowired
    private lateinit var mapper: ObjectMapper

    fun readConfig() : FearGreedData?
    {
        val file = File(fGIFile)

        if (!file.exists())
        {
            logger.info("File does not exist!")
            return null
        }

        return mapper.readValue(file, FearGreedData::class.java)
    }

    fun writeConfig(fearGreedData: FearGreedData)
    {
        mapper.writeValue(File(fGIFile), fearGreedData)
    }
}