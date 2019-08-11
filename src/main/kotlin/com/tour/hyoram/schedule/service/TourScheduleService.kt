package com.tour.hyoram.schedule.service

import com.tour.hyoram.schedule.model.Schedule
import com.tour.hyoram.schedule.repository.TourScheduleRepository
import org.springframework.stereotype.Service

@Service
class TourScheduleService(private val tourScheduleRepository: TourScheduleRepository) {
    private val tourScheduleParser = TourScheduleParser()

    fun readTourSchedules(): List<Schedule> {
        return tourScheduleParser.parseTourScheduleToDto(tourScheduleRepository.fetchTourSchedules())
    }

}
