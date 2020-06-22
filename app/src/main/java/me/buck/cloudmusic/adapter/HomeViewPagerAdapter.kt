package me.buck.cloudmusic.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.blankj.utilcode.util.StringUtils
import me.buck.cloudmusic.R
import me.buck.cloudmusic.fragment.DiscoveryFragment
import me.buck.cloudmusic.fragment.MyFragment
import me.buck.cloudmusic.fragment.VideoFragment
import me.buck.cloudmusic.fragment.VillainFragment

class HomeViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MyFragment.newInstance()
            1 -> DiscoveryFragment.newInstance()
            2 -> VillainFragment.newInstance()
            3 -> VideoFragment.newInstance()
            else -> throw RuntimeException("fragment 数量超出")
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> StringUtils.getString(R.string.tab_item_my)
            1 -> StringUtils.getString(R.string.tab_item_discovery)
            2 -> StringUtils.getString(R.string.tab_item_villain)
            3 -> StringUtils.getString(R.string.tab_item_video)
            else -> throw RuntimeException("fragment 数量超出")
        }
    }
}