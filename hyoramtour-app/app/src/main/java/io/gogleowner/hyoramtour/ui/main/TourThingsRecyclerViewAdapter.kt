package io.gogleowner.hyoramtour.ui.main

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.gogleowner.hyoramtour.R
import io.gogleowner.hyoramtour.dto.TourThing


class TourThingsRecyclerViewAdapter(private val tourThings: List<TourThing>)
    : RecyclerView.Adapter<TourThingsRecyclerViewAdapter.TourThingViewHolder>() {

    private val copiedTourThings: MutableList<TourThing>  = mutableListOf()

    init {
        copiedTourThings.addAll(tourThings)
    }

    fun updateTourThings(tourThings: List<TourThing>) {
        copiedTourThings.clear()
        copiedTourThings.addAll(tourThings)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourThingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tour_things_item, parent, false)

        return TourThingViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: TourThingViewHolder, position: Int) {
        val tourThing = copiedTourThings[position]

        if (tourThing.title.isNotBlank() || tourThing.description.isNotBlank()) {

            if (tourThing.title.isNotBlank()) {
                viewHolder.titleTextView.text = tourThing.title
            } else {
                viewHolder.titleTextView.visibility = View.GONE
            }

            viewHolder.descriptionTextView.text = tourThing.description
            viewHolder.themeImageView.setImageResource(ThemeImage.getResource(tourThing.theme))

            if (tourThing.contentTheme.isNotBlank()) {
                viewHolder.contentImageView.setImageResource(ThemeImage.getResource(tourThing.contentTheme))
            } else {
                viewHolder.contentImageView.visibility = View.GONE
            }

        } else {
            viewHolder.invisibleViews()
        }
    }


    override fun getItemCount(): Int {
        return copiedTourThings.size
    }


    class TourThingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val themeImageView: ImageView = itemView.findViewById(R.id.theme_image)
        val contentImageView: ImageView = itemView.findViewById(R.id.content_image)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.thing_description)

        fun invisibleViews() {
            themeImageView.visibility = View.GONE
            titleTextView.visibility = View.GONE
            contentImageView.visibility = View.GONE
            descriptionTextView.visibility = View.GONE
        }
    }

    private enum class ThemeImage(private val resource: Int) {
        오전(R.mipmap.sunrise),
        오후(R.mipmap.sunset),
        저녁(R.mipmap.evening),
        종일(R.mipmap.allday),
        기억해야할것(R.mipmap.remember),
        숙소(R.mipmap.room),
        비용(R.mipmap.euro),

        버스(R.mipmap.bus),
        비행(R.mipmap.flight),
        쇼핑(R.mipmap.shopping),
        투어(R.mipmap.tour),
        자유(R.mipmap.free),
        기차(R.mipmap.train),

        UNKNOWN(R.drawable.ic_priority_high_black_24dp)
        ;

        companion object {
            private val themeImageMap = values().associateBy({ it.name }, { it.resource })

            fun getResource(theme: String): Int = themeImageMap.getOrElse(theme, { UNKNOWN.resource })
        }
    }

}
