package io.gogleowner.hyoramtour.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.tour.hyoram.schedule.model.DailySchedule
import io.gogleowner.hyoramtour.R

class ScheduleContentListViewAdapter(
    private val context: Context, private val contents: MutableList<DailySchedule.Content>)
    : BaseExpandableListAdapter() {

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = convertView ?: layoutInflater.inflate(R.layout.schedule_content, null)

        val textView: TextView = view.findViewById(R.id.scheduleContentText)
        textView.text = contents[groupPosition].content

        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = convertView ?: layoutInflater.inflate(R.layout.schedule_detail, null)

        val textView: TextView = view.findViewById(R.id.scheduleDetailText)
        textView.text = contents[groupPosition].detail

        return view
    }

    override fun getGroup(groupPosition: Int): Any {
        return contents[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return contents[groupPosition].detail
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return contents.size
    }

}