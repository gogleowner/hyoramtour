package io.gogleowner.hyoramtour.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tour.hyoram.schedule.model.Schedule
import com.tour.hyoram.schedule.model.TourScheduleData
import io.gogleowner.hyoramtour.R

object SharedData {
    var tourSchedules: MutableList<Schedule> = mutableListOf()
    var lastModifiedDateTime: String = ""// FIXME 앱 실행되는 region에 따라 파싱하는 DateTime 으로 변경 필요

    fun tabTexts() = tourSchedules.map { "${it.day} (${it.date})" }

    fun updateTourSchedules(tourScheduleData: TourScheduleData) {
        tourSchedules.clear()
        tourSchedules.addAll(tourScheduleData.schedules)

        lastModifiedDateTime = tourScheduleData.lastModifiedDateTime
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
                    Snackbar.make(view!!, "${SharedData.lastModifiedDateTime} 시간 데이터로 업데이트했습니다.", Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(view!!, "네트워크에 연결할 수 없습니다.", Snackbar.LENGTH_LONG).show()
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