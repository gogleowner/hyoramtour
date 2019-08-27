package com.tour.hyoram.schedule.service

import com.tour.hyoram.schedule.model.DailySchedule
import com.tour.hyoram.schedule.model.Schedule

class TourScheduleParser {
    private val keys =
        arrayOf("day", "date", "timeUnit", "content", "theme", "detail", "cost", "rememberThings", "room")

    fun parseTourScheduleToDto(responseValue: MutableList<MutableList<Any>>): List<Schedule> {
        val tourSchedules = preProcessToBeParsed(responseValue)

        val parsedSchedules = mutableListOf<Schedule>()
        var schedule: Schedule? = null
        var dailySchedule: DailySchedule? = null
        var room = ""
        for ((index, row) in tourSchedules.withIndex()) {
            if (row[0].isNotBlank()) {
                room = if (index != tourSchedules.size - 1) {
                    if (row[8].isNotBlank()) row[8] else room
                } else ""

                schedule = Schedule(row[0], row[1], row[7], room)

                dailySchedule = DailySchedule(row[2])
                dailySchedule.addContent(row[3], row[4], row[5])
                schedule.addDailySchedule(dailySchedule)
                schedule.addCost(row[6])
                parsedSchedules.add(schedule)
            } else {
                if (schedule == null || dailySchedule == null) {
                    throw RuntimeException("schedule 또는 dailySchedule이 비어있습니다. [$row]")
                }

                schedule.addCost(row[6])
                if (row[2].isNotBlank()) {
                    dailySchedule = DailySchedule(row[2])
                    dailySchedule.addContent(row[3], row[4], row[5])
                    schedule.addDailySchedule(dailySchedule)
                } else {
                    dailySchedule.addContent(row[3], row[4], row[5])

                }
            }
        }

        return parsedSchedules.toList()
    }

    private fun preProcessToBeParsed(responseValue: MutableList<MutableList<Any>>): List<Array<String>> {
        val tourSchedules: MutableList<Array<String>> = mutableListOf()
        for (row in responseValue) {
            val schedule = Array(keys.size) { _ -> "" }
            for ((index, col) in row.withIndex()) {
                schedule[index] = col.toString()
            }

            tourSchedules.add(schedule)
        }

        return tourSchedules.toList()
    }
}
