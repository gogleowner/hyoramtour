package io.gogleowner.hyoramtour.ui.main

import android.os.AsyncTask
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tour.hyoram.schedule.model.Schedule
import java.net.URL

open class TourScheduleRequesterAsyncTask : AsyncTask<Unit, Unit, String>() {

    override fun onPreExecute() {
        Log.i("[TourScheduleRequest]", "실행되는거 맞음/??")
    }

    override fun doInBackground(vararg params: Unit?): String {
        Log.i("[TourScheduleRequest]", "doInBackground")
        return URL("http://10.0.2.2:8080/tourSchedules").readText()
    }

    override fun onPostExecute(result: String?) {
        Log.i("[TourScheduleRequest]", "completed")
        Log.i("[TourScheduleRequest]", result)

        result?.let {
            Log.i("[TourScheduleRequest]", "스케쥴 데이터 업데이트 ~~")

            val updatedSchedules: List<Schedule> = jacksonObjectMapper().readValue(it)

            SharedData.updateTourSchedules(updatedSchedules)

        }

        super.onPostExecute(result)
    }

}
