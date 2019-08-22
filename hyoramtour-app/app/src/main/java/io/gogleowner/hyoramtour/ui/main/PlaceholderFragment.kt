package io.gogleowner.hyoramtour.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

                val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

                if (activeNetwork != null && activeNetwork.isConnected) {
                    refreshTourSchedules()
                    Toast.makeText(context, "스케쥴 데이터를 업데이트 했습니다.", Toast.LENGTH_LONG)
                } else {
                    Toast.makeText(context, "네트워크에 연결할 수 없습니다.", Toast.LENGTH_LONG)
                }

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