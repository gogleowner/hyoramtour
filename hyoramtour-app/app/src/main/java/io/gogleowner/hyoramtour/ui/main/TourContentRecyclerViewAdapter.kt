package io.gogleowner.hyoramtour.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tour.hyoram.schedule.model.DailySchedule
import io.gogleowner.hyoramtour.R

class TourContentRecyclerViewAdapter(private val dailyScheduleContents: List<DailySchedule.Content>)
    : RecyclerView.Adapter<TourContentRecyclerViewAdapter.TourContentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TourContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tour_content_item, parent, false)

        return TourContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dailyScheduleContents.size
    }

    override fun onBindViewHolder(viewHolder: TourContentViewHolder, position: Int) {
        val content = dailyScheduleContents[position]

        viewHolder.contentTextView.text = content.content
        viewHolder.detailTextView.text = content.detail
        // TODO tour image
    }


    class TourContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentTextView: TextView = itemView.findViewById(R.id.content)
        val detailTextView: TextView = itemView.findViewById(R.id.detail)
    }

}