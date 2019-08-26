package io.gogleowner.hyoramtour

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.gogleowner.hyoramtour.ui.main.SharedData
import io.gogleowner.hyoramtour.ui.main.TourScheduleRequesterAsyncTask


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork != null && activeNetwork.isConnected) {
            object : TourScheduleRequesterAsyncTask() {
                override fun onPostExecute(result: String?) {
                    super.onPostExecute(result)

                    moveToMainActivity()
                }
            }.execute()
        } else {
            val sharedPreferences = getSharedPreferences("tourSchedule", Context.MODE_PRIVATE)

            val tourSchedules = sharedPreferences.getString("tourSchedule", "")

            if (tourSchedules.isNotEmpty()) {
                SharedData.updateTourSchedules(jacksonObjectMapper().readValue(tourSchedules))
                moveToMainActivity()
            } else {
                Toast.makeText(applicationContext, "저장된 캐시 데이터가 없습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun moveToMainActivity() {
        val mainClass = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(mainClass)
        finish()
    }

}
