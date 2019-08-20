package io.gogleowner.hyoramtour.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tour.hyoram.schedule.model.Schedule
import io.gogleowner.hyoramtour.R
import java.net.URL

object SharedData {
    var tourSchedules: MutableList<Schedule> = mutableListOf()

    fun tabTexts() = tourSchedules.map { "${it.day} (${it.date})" }

    fun updateTourSchedules(updatedTourSchedules: List<Schedule>) {
        tourSchedules.clear()
        tourSchedules.addAll(updatedTourSchedules)
    }
}

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var tourThingsRecyclerView: RecyclerView
    private lateinit var tourThingsRecyclerViewAdapter: TourThingsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        refreshTourSchedules()

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

        tourThingsRecyclerView = root.findViewById(R.id.tour_things_recycler_view)

        tourThingsRecyclerViewAdapter = TourThingsRecyclerViewAdapter(pageViewModel.getTourThings())
        tourThingsRecyclerView.adapter = tourThingsRecyclerViewAdapter
        tourThingsRecyclerView.layoutManager = LinearLayoutManager(context!!)

        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.apply {
            setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
            )

            setOnRefreshListener {
                Log.d("[PlaceholderFragment]", "refresh!!")

                refreshTourSchedules()
                onRefreshComplete()
            }
        }
        tourThingsRecyclerViewAdapter.notifyDataSetChanged()

        return root
    }

    fun refreshTourSchedules() {
        Log.i("[PlaceholderFragment]", "initiateRefresh")

        TourScheduleRequesterAsyncTask().execute()
    }

    fun onRefreshComplete() {
        Log.i("[PlaceholderFragment]", "completeRefresh")

        pageViewModel.schedule = SharedData.tourSchedules[pageViewModel.getIndex()]


        tourThingsRecyclerViewAdapter.updateTourThings(pageViewModel.getTourThings())


        tourThingsRecyclerViewAdapter.notifyDataSetChanged()

        tourThingsRecyclerView.scrollToPosition(0)
        swipeRefreshLayout.isRefreshing = false
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