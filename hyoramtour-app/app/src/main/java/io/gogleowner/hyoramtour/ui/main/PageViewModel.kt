package io.gogleowner.hyoramtour.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
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

    fun getTourThings(): List<TourThing> {
        val tourThings = mutableListOf<TourThing>()
        tourThings.addAll(getDailyContentsAsTourThings())
        tourThings.addAll(getTextTourThings())

        return tourThings
    }

    fun getTextTourThings() : List<TourThing> {
        return listOf(
            TourThing("비용", "${schedule.cost}€"),
            TourThing("기억해야할 것", schedule.rememberThings),
            TourThing("숙소", schedule.room)
        )
    }

    fun getDailyContentsAsTourThings(): List<TourThing> {
        return getDailyContents().map { TourThing(title = it.content, description = it.detail) }
    }

    fun getDailyContents(): List<DailySchedule.Content> {
        return schedule.dailySchedules.flatMap { schedule ->
            schedule.contents
                .filter { it.content.isNotBlank() }
                .map { it.copy(content = "<${schedule.timeUnit}> ${it.content}") }
        }
    }

}