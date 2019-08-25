package com.tour.hyoram.schedule.repository

interface TourScheduleRepository {
    fun fetchTourSchedules(): MutableList<MutableList<Any>>
    fun fetchLastModifiedDateTime(): String
}
