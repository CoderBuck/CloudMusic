package me.buck.cloudmusic.activity

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ColorUtils
import me.buck.cloudmusic.R
import me.buck.cloudmusic.adapter.HomeViewPagerAdapter
import me.buck.cloudmusic.databinding.ActivityHomeBinding
import me.buck.cloudmusic.util.TabUtils
import org.youma.util.contentView
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    lateinit var bind: ActivityHomeBinding
    lateinit var homeAdapter: HomeViewPagerAdapter

    val argbEvaluator = ArgbEvaluator()
    val startColor = ColorUtils.getColor(R.color.tab_normal)
    val endColor = ColorUtils.getColor(R.color.tab_select)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bind = ActivityHomeBinding.bind(contentView)
        setupInsets()
        homeAdapter = HomeViewPagerAdapter(supportFragmentManager)
        bind.viewPager.adapter = homeAdapter
        bind.tabLayout.setupWithViewPager(bind.viewPager)
        bind.viewPager.currentItem = 1
    }

    private fun setupInsets() {
        bind.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        bind.appbar.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
    }
}