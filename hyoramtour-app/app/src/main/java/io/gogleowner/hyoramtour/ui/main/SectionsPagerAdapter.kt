package io.gogleowner.hyoramtour.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        Log.d("[TAB]", "으아아아ㅏ앙아아아아아아아아 ${SharedData.tabTexts()[position]}")
        return SharedData.tabTexts()[position] // context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        Log.d("[TAB]", "여기냐 설마아아아아아아아아아 ${SharedData.tabTexts().size}")
        return SharedData.tabTexts().size
    }
}