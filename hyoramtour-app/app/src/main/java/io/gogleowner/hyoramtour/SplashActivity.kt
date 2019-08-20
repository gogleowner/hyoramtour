package io.gogleowner.hyoramtour

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.gogleowner.hyoramtour.ui.main.TourScheduleRequesterAsyncTask


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        object : TourScheduleRequesterAsyncTask() {
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                val mainClass = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(mainClass)
                finish()
            }
        }.execute()

    }

}
