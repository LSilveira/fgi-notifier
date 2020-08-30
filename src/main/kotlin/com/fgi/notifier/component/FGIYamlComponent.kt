package com.fgi.notifier.component

import com.fasterxml.jackson.databind.ObjectMapper
import com.fgi.notifier.model.FearGreedData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File

@Component
class FGIYamlComponent
{
    private val fGIFile = "fearGreedIndex.yaml"

    @Autowired
    private lateinit var mapper: ObjectMapper

    fun readConfig() : FearGreedData?
    {
        val file = File(fGIFile)

        if (!file.exists())
            return null

        return mapper.readValue(file, FearGreedData::class.java)
    }

    fun writeConfig(fearGreedData: FearGreedData)
    {
        mapper.writeValue(File(fGIFile), fearGreedData)
    }
}