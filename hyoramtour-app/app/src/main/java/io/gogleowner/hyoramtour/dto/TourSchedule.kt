package com.tour.hyoram.schedule.model

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
    val cost: Double,
    val rememberThings: String,
    val room: String) {

    constructor(day: String, date: String, cost: String, rememberThings: String, room: String) :
            this(day, date, if (cost.isNotBlank()) cost.toDouble() else 0.0, rememberThings, room)

    val dailySchedules: MutableList<DailySchedule> = mutableListOf()

    fun addDailySchedule(dailySchedule: DailySchedule) {
        dailySchedules.add(dailySchedule)
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

enum class Theme(val korean: String, emoticon: String = "") {
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
        private val themeByKoreanMap = values().associateBy({ it.korean }, { value -> value })

        fun themeByKorean(korean: String): Theme {
            return themeByKoreanMap.getValue(korean)
        }
    }
}
