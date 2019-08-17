package io.gogleowner.hyoramtour.ui.main

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.tour.hyoram.schedule.model.DailySchedule
import io.gogleowner.hyoramtour.R
import io.gogleowner.hyoramtour.dto.TourThing


class TourThingsRecyclerViewAdapter(private val tourThings: List<TourThing>)
    : RecyclerView.Adapter<TourThingsRecyclerViewAdapter.TourThingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourThingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tour_things_item, parent, false)

        return TourThingViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: TourThingViewHolder, position: Int) {
        val tourThing = tourThings[position]

        viewHolder.titleTextView.text = tourThing.title
        viewHolder.descriptionTextView.text = tourThing.description

//        viewHolder.contentRecyclerView.apply {
//            layoutManager = LinearLayoutManager(viewHolder.contentRecyclerView.context, LinearLayout.VERTICAL, false)
//            adapter = TourContentRecyclerViewAdapter(dailyContents)
//            setRecycledViewPool(RecyclerView.RecycledViewPool())
//        }

    }


    override fun getItemCount(): Int {
        return tourThings.size
    }


    class TourThingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.thing_description)
//        val contentRecyclerView: RecyclerView = itemView.findViewById(R.id.content_recycler_view)
    }


}