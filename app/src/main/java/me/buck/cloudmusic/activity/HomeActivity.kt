package me.buck.cloudmusic.activity

import android.os.Bundle
import android.os.Handler
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import me.buck.cloudmusic.R
import me.buck.cloudmusic.adapter.HomeViewPagerAdapter
import me.buck.cloudmusic.databinding.ActivityHomeBinding
import org.youma.util.contentView
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    lateinit var bind: ActivityHomeBinding
    lateinit var homeAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bind = ActivityHomeBinding.bind(contentView)
        homeAdapter = HomeViewPagerAdapter(supportFragmentManager)
        setupInsets()
        initView()
    }

    private fun initView() {
        bind.apply {
            viewPager.adapter = homeAdapter
            tabLayout.setupWithViewPager(viewPager)
            viewPager.currentItem = 1
        }
    }

    private fun setupInsets() {
        bind.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        bind.appbar.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
    }
}