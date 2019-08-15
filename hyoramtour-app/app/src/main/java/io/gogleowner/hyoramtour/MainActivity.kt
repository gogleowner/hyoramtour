package io.gogleowner.hyoramtour

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.gogleowner.hyoramtour.ui.main.SectionsPagerAdapter
import io.gogleowner.hyoramtour.ui.main.SharedData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("[FILE]" , "파일을 읽읍시다.")
        SharedData.tourSchedules =
            jacksonObjectMapper().readValue(resources.openRawResource(R.raw.parsed_schedule))

        Log.d("[FILE]" , "파일을 읽어따아. ${SharedData.tourSchedules.size}")

        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }
}
