package io.gogleowner.hyoramtour.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ListView
import com.tour.hyoram.schedule.model.Schedule
import io.gogleowner.hyoramtour.R

object SharedData {
    var tourSchedules: List<Schedule> = emptyList()

    fun tabTexts() = tourSchedules.map { "${it.day} (${it.date})" }
}

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            val index = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
            this.schedule = SharedData.tourSchedules[index]
            setIndex(index)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val tourThingsRecyclerView: RecyclerView = root.findViewById(R.id.tour_things_recycler_view)
        tourThingsRecyclerView.adapter = TourThingsRecyclerViewAdapter(pageViewModel.getTourThings())
        tourThingsRecyclerView.layoutManager = LinearLayoutManager(context!!)

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}