package io.gogleowner.hyoramtour.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.tour.hyoram.schedule.model.DailySchedule
import com.tour.hyoram.schedule.model.Schedule
import io.gogleowner.hyoramtour.dto.TourThing

class PageViewModel : ViewModel() {
    private val _index = MutableLiveData<Int>()

    lateinit var schedule: Schedule

    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getIndex(): Int {
        return _index.value!!
    }

    fun getTourThings(): List<TourThing> {
        val tourThings = mutableListOf<TourThing>()
        tourThings.addAll(getDailyContentsAsTourThings())
        tourThings.addAll(getTextTourThings())

        return tourThings
    }

    fun getTextTourThings() : List<TourThing> {
        return listOf(
            TourThing("비용", "${schedule.cost}€", "비용"),
            TourThing("", schedule.rememberThings, "기억해야할것"),
            TourThing("숙소", schedule.room, "숙소")
        )
    }

    fun getDailyContentsAsTourThings(): List<TourThing> {
        return schedule.dailySchedules.flatMap { schedule ->
            schedule.contents
                .filter { it.content.isNotBlank() }
                .map { TourThing(it.content, it.detail, schedule.timeUnit, it.theme.korean) }
        }
    }

}
