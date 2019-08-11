package com.tour.hyoram.schedule.controller

import com.tour.hyoram.schedule.model.Schedule
import com.tour.hyoram.schedule.service.TourScheduleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TourScheduleController(val tourScheduleService: TourScheduleService) {
    @GetMapping("/tourSchedules")
    fun getTourSchedules(): List<Schedule> {
        return tourScheduleService.readTourSchedules()
    }
}