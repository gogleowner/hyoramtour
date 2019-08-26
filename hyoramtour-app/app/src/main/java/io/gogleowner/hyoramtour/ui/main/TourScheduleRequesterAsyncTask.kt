package io.gogleowner.hyoramtour.ui.main

import android.os.AsyncTask
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tour.hyoram.schedule.model.Schedule
import com.tour.hyoram.schedule.model.TourScheduleData
import java.net.URL

open class TourScheduleRequesterAsyncTask : AsyncTask<Unit, Unit, String>() {

    override fun doInBackground(vararg params: Unit?): String {
        Log.i("[TourScheduleRequest]", "doInBackground")
//        return URL("http://10.0.2.2:8080/tourSchedules").readText()
        return URL("http://35.247.123.71:8080/tourSchedules").readText()
    }

    override fun onPostExecute(result: String?) {
        Log.i("[TourScheduleRequest]", "onPostExecute")

        result?.let {
            Log.i("[TourScheduleRequest]", "스케쥴 데이터 업데이트 ~~")

            val updatedSchedules: TourScheduleData = jacksonObjectMapper().readValue(it)

            SharedData.updateTourSchedules(updatedSchedules)
        }
    }

}
