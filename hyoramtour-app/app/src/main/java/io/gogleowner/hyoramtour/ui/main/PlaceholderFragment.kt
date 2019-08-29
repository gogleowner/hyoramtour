package io.gogleowner.hyoramtour.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
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
import java.util.*

object SharedData {
    var tourSchedules: MutableList<Schedule> = mutableListOf()
    var lastModifiedDateTime: String = ""

    fun tabTexts() = tourSchedules.map { "${it.day} (${it.date})" }

    fun updateTourSchedules(tourScheduleData: TourScheduleData) {
        tourSchedules.clear()
        tourSchedules.addAll(tourScheduleData.schedules)

        lastModifiedDateTime = tourScheduleData.lastModifiedDateTime
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun formattedLastModifiedDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val parsedDate: Date = dateFormat.parse(lastModifiedDateTime)

        val uiDateFormat = SimpleDateFormat("yyyy년 M월 d일 H시 m분 s초")
        uiDateFormat.timeZone = TimeZone.getDefault()

        return "${uiDateFormat.format(parsedDate)} (${TimeZone.getDefault().displayName})"
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

    @RequiresApi(Build.VERSION_CODES.N)
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

                    Snackbar.make(view!!, "${SharedData.formattedLastModifiedDateTime()} 데이터로 업데이트했습니다.", Snackbar.LENGTH_LONG).show()
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