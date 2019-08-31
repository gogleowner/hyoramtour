package com.tour.hyoram.schedule.model

import java.math.BigDecimal

/*
[
	{
		"day": "1일차",
		"date": "2019.09.02",
		"cost": 0
		"timeUnit": 123
		"rememberThings": "",
		"room": "",
		"allDay": false,
		"dailySchedule": [
			{
				"timeUnit": "오전",
				"contents": [
					{
						"content": "",
						"theme": "",
						"detail": ""
					}
				]
			},
			{
				"timeUnit": "오후",
				"contents": [
					{
						"content": "",
						"theme": "",
						"detail": ""
					},
				]
			}
		]
	}
]
 */
data class TourScheduleData(
    val schedules: List<Schedule>,
    val lastModifiedDateTime: String)

data class Schedule(
    val day: String,
    val date: String,
    val rememberThings: String,
    val room: String) {

    private var costAsBigDecimal = BigDecimal.ZERO
    val cost: Double
    get() = costAsBigDecimal.toDouble()

    val dailySchedules: MutableList<DailySchedule> = mutableListOf()

    fun addDailySchedule(dailySchedule: DailySchedule) {
        dailySchedules.add(dailySchedule)
    }

    fun addCost(costAsString: String) {
        if (costAsString.isNotBlank()) {
            costAsBigDecimal += costAsString.toBigDecimal()
        }
    }
}

data class DailySchedule(val timeUnit: String) {
    val contents = mutableListOf<Content>()

    fun addContent(content: String, theme: String, detail: String) {
        contents.add(Content(content, Theme.themeByKorean(theme), detail))
    }

    data class Content(
        val content: String,
        val theme: Theme,
        val detail: String
    )
}

enum class Theme(val korean: String) {
    /*테마 : 비행 , 버스 , 기차 , 자유 , 투어 , 쇼핑*/
    FLIGHT("비행"),
    BUS("버스"),
    TRAIN("기차"),
    FREE("자유"),
    TOUR("투어"),
    SHOPPING("쇼핑"),

    NONE("")
    ;

    companion object {
        private val themeByKoreanMap = values().associateBy({ it.korean }, { it })

        fun themeByKorean(korean: String): Theme {
            return themeByKoreanMap.getValue(korean)
        }
    }
}
