package io.gogleowner.hyoramtour.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.tour.hyoram.schedule.model.DailySchedule
import io.gogleowner.hyoramtour.R

class ScheduleExpandableListViewAdapter(
    private val context: Context,
    private val dailySchedules: MutableList<DailySchedule>
) : BaseExpandableListAdapter() {

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = convertView ?: layoutInflater.inflate(R.layout.schedule_header, null)
        val textView: TextView = view.findViewById(R.id.scheduleHeaderText)
        textView.text = dailySchedules[groupPosition].timeUnit

        return view
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup?
    ): View {
        val scheduleContentListView = ScheduleContentListView(context)

        scheduleContentListView.setAdapter(
            ScheduleContentListViewAdapter(context, dailySchedules[groupPosition].contents))
        scheduleContentListView.setGroupIndicator(null)

        return scheduleContentListView
    }

    override fun getGroup(groupPosition: Int): Any = groupPosition

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean = true

    override fun getChildrenCount(groupPosition: Int): Int = 1

    override fun getChild(groupPosition: Int, childPosition: Int): Any = childPosition

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int = dailySchedules.size

}