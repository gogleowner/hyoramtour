package com.tour.hyoram.schedule.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tour.hyoram.schedule.model.*
import org.junit.jupiter.api.Test

internal class TourScheduleParserTest {
    private val tourScheduleParser = TourScheduleParser()

    @Test
    fun parseTourScheduleToDto() {
        this::class.java.getResourceAsStream("/google-sheet-schedule.json")

        val jacksonObjectMapper = jacksonObjectMapper()

        val tourScheduleResponse: MutableList<MutableList<Any>> =
            jacksonObjectMapper.readValue(this::class.java.getResourceAsStream("/google-sheet-schedule.json"))

        val actual = tourScheduleParser.parseTourScheduleToDto(tourScheduleResponse)

        val expect = jacksonObjectMapper.readValue<List<Schedule>>(
            this::class.java.getResourceAsStream("/parsed-schedule.json"))

        assert(actual == expect)
    }
}
