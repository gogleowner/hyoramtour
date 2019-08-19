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
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

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

        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            Log.d("[PlaceholderFragment]", "refresh!!")

            refreshTourSchedules()
        }

        return root
    }

    fun refreshTourSchedules() {
        Log.i("[PlaceholderFragment]", "initiateRefresh")

        object : AsyncTask<Unit, Unit, String>() {
            override fun doInBackground(vararg params: Unit?): String {
                Log.i("[TourScheduleRequest]", "doInBackground")
                Thread.sleep(2000L)

                return "completed"
            }

            override fun onPostExecute(result: String?) {
                Log.i("[TourScheduleRequest]", "completed")

                super.onPostExecute(result)
                onRefreshComplete()
            }
        }.execute()
    }

    fun onRefreshComplete() {
        Log.i("[PlaceholderFragment]", "completeRefresh")
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