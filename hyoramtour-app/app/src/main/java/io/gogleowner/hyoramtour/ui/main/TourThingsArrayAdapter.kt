package io.gogleowner.hyoramtour.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tour.hyoram.schedule.model.Schedule
import io.gogleowner.hyoramtour.R
import kotlinx.android.synthetic.main.tour_things_item.view.*

data class TourThing(val title: String, val description: String)

val tourThings = listOf(
    TourThing("비용", "1"),
    TourThing("기억해야할 것", "로마 교통권 구입, 개인이어폰"),
    TourThing("숙소", "9월 03일 – 9월 08일 \n주소 14 Via Enrico Cialdini, 로마, Lazio 00185, 이탈리아\nhttps://www.airbnb.co.kr/rooms/15402806?s=51\n체크인/체크아웃 14:00-22:00/11:00\nTourist tax 3.5/명\nincluded Breakfast")
)

/*
    비용 : ~~ 유로
    기억해야할 것 : 로마 교통권 구입, 개인이어폰
    숙소 :

 */

class TourThingsArrayAdapter(context: Context, private val schedule: Schedule)
    : ArrayAdapter<TourThing>(context, 0, tourThings) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = convertView ?: LayoutInflater.from(context).inflate(R.layout.tour_things_item, parent, false)

        rootView.thingTitle.text = tourThings[position].title
        rootView.thingDescription.text = when(position) {
            0 -> schedule.cost.toString()
            1 -> schedule.rememberThings
            2 -> schedule.room
            else -> throw IllegalArgumentException()
        }


        return rootView
    }
}