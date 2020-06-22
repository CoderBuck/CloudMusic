package me.buck.cloudmusic.util

import android.widget.TextView
import com.google.android.material.tabs.TabLayout

object TabUtils {

    fun getTextView(tabView: TabLayout.TabView): TextView {
        for (i in 0 until tabView.childCount) {
            val childView = tabView.getChildAt(i)
            if (childView is TextView) return childView
        }
        throw RuntimeException("can not find TextView!")
    }

    fun getTextView(tabLayout: TabLayout, index: Int): TextView {
        val tabView = tabLayout.getTabAt(index)!!.view
        return getTextView(tabView)
    }
}