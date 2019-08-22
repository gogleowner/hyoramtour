package io.gogleowner.hyoramtour

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.gogleowner.hyoramtour.ui.main.SectionsPagerAdapter
import io.gogleowner.hyoramtour.ui.main.SharedData

class MainActivity : AppCompatActivity() {
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onResume() {
        super.onResume()

        sectionsPagerAdapter.notifyDataSetChanged()
    }

    override fun onStop() {
        Log.i("MainActivity", "onStop")
        super.onStop()

        val sharedPreferences = getSharedPreferences("tourSchedule", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("tourSchedule", jacksonObjectMapper().writeValueAsString(SharedData.tourSchedules))

        Log.i("MainActivity", "tourSchedule shared prefrences 에 저장 => ${editor.commit()}")
    }
}
