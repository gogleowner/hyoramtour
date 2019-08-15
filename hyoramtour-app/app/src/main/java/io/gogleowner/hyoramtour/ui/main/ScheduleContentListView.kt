package io.gogleowner.hyoramtour.ui.main

import android.content.Context
import android.widget.ExpandableListView

class ScheduleContentListView(context: Context) : ExpandableListView(context) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(99999, MeasureSpec.AT_MOST))
    }
}