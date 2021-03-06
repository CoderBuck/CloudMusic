package me.buck.cloudmusic.activity

import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.updatePadding
import me.buck.cloudmusic.Config
import me.buck.cloudmusic.R
import me.buck.cloudmusic.adapter.HomeViewPagerAdapter
import me.buck.cloudmusic.databinding.ActivityHomeBinding
import me.buck.cloudmusic.util.delegate.viewBinding

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    private val bind by viewBinding(ActivityHomeBinding::bind)
    private lateinit var homeAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInsets()
        homeAdapter = HomeViewPagerAdapter(supportFragmentManager)
        initView()
    }

    private fun initView() {
        bind.apply {
            // drawer
            menu.setOnClickListener {
                val drawerOpen = drawerLayout.isDrawerOpen(GravityCompat.START)
                if (drawerOpen) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
            }

            // viewpager
            viewPager.adapter = homeAdapter
            tabLayout.setupWithViewPager(viewPager)
            viewPager.currentItem = 1
        }
    }

    private fun setupInsets() {
        bind.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        bind.appbar.updatePadding(top = Config.statusBarHeight)
    }
}