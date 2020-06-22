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
        initView()

        val textView = TabUtils.getTextView(bind.tabLayout, 0)
        textView.text
        Timber.d("textView = %s", textView.text)


    }

    private fun initView() {
        bind.apply {
            viewPager.offscreenPageLimit = 1
            viewPager.adapter = homeAdapter
            viewPager.currentItem = 1
            tabLayout.setupWithViewPager(viewPager)


            val textView1 = TabUtils.getTextView(tabLayout, 0)
            val textView2 = TabUtils.getTextView(tabLayout, 1)
            val textView3 = TabUtils.getTextView(tabLayout, 2)
            val textView4 = TabUtils.getTextView(tabLayout, 3)
            val tabs = listOf(textView1, textView2, textView3, textView4)

            tabs.forEachIndexed { index, textView ->
                textView.updateLayoutParams {
                    width = ViewGroup.LayoutParams.MATCH_PARENT
                    height = ViewGroup.LayoutParams.MATCH_PARENT
                }
                if (index == 1) {
                    textView.setTextColor(endColor)
                    textView.scaleX = 1.2f
                    textView.scaleY = 1.2f
                } else {
                    textView.setTextColor(startColor)
                }
            }

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                var isDragging = false  //
                var isSettling = false
                var oldItem = 0
                var draggingOffset = 0f


                override fun onPageScrollStateChanged(state: Int) {
                    Timber.d("onPageScrollStateChanged state = %s,  pos = %s", state, viewPager.currentItem)
                    when (state) {
                        ViewPager.SCROLL_STATE_DRAGGING -> {
                            isDragging = true
                            oldItem = viewPager.currentItem
                        }
                        ViewPager.SCROLL_STATE_SETTLING -> {
                            isSettling = true
                        }
                        else -> {
                            isDragging = false
                            isSettling = false
                            draggingOffset = 0f
                        }
                    }

                    if (state != ViewPager.SCROLL_STATE_IDLE) {

                    }
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    Timber.d("onPageScrolled curpos = %s", viewPager.currentItem)
                    Timber.d("onPageScrolled %s,  %s,  %s", position, positionOffset, positionOffsetPixels)

                    if (isDragging) {

                        // 向右滑动  positionOffset 0 --> 1
                        if (oldItem == position && position < viewPager.childCount) {


                            if (positionOffset in 0.1f..0.9f) {

                                if (Math.abs(draggingOffset - positionOffset) < 0.05f) {
                                    return
                                }
                                draggingOffset = positionOffset

                                val fraction = (positionOffset - 0.1f) / 0.8f
                                val newPosColor = argbEvaluator.evaluate(fraction, startColor, endColor) as Int
                                val oldPosColor = argbEvaluator.evaluate(fraction, endColor, startColor) as Int
                                tabs[oldItem + 1].setTextColor(newPosColor)
                                tabs[oldItem].setTextColor(oldPosColor)

                                tabs[oldItem + 1].scaleX = fraction * 0.2f + 1.0f
                                tabs[oldItem + 1].scaleY = fraction * 0.2f + 1.0f
                                tabs[oldItem].scaleX = (1 - fraction) * 0.2f + 1.0f
                                tabs[oldItem].scaleY = (1 - fraction) * 0.2f + 1.0f

                            }

                            if (positionOffset < 0.1f) {
                                tabs[oldItem].setTextColor(endColor)
                                tabs[oldItem + 1].setTextColor(startColor)

                                tabs[oldItem + 1].scaleX = 1.0f
                                tabs[oldItem + 1].scaleY = 1.0f
                                tabs[oldItem].scaleX = 1.2f
                                tabs[oldItem].scaleY = 1.2f
                            }

                            if (positionOffset > 0.9f) {
                                tabs[oldItem + 1].setTextColor(endColor)
                                tabs[oldItem].setTextColor(startColor)

                                tabs[oldItem + 1].scaleX = 1.2f
                                tabs[oldItem + 1].scaleY = 1.2f
                                tabs[oldItem].scaleX = 1.0f
                                tabs[oldItem].scaleY = 1.0f
                            }

                        }
                        // 向左滑动 positionOffset 1 --> 0
                        else if (oldItem - position == 1) {
                            tabs[position].setTextColor(ColorUtils.getColor(R.color.red_400))
                            tabs[position + 1].setTextColor(ColorUtils.getColor(R.color.grey_400))

                            if (positionOffset in 0.1f..0.9f) {

                                if (Math.abs(draggingOffset - positionOffset) < 0.05f) {
                                    return
                                }
                                draggingOffset = positionOffset
                                val fraction = (positionOffset - 0.1f) / 0.8f
                                val oldPosColor = argbEvaluator.evaluate(fraction, startColor, endColor) as Int
                                val newPosColor = argbEvaluator.evaluate(fraction, endColor, startColor) as Int
                                tabs[oldItem].setTextColor(oldPosColor)
                                tabs[oldItem - 1].setTextColor(newPosColor)

                                tabs[oldItem].scaleX = fraction * 0.2f + 1.0f
                                tabs[oldItem].scaleY = fraction * 0.2f + 1.0f
                                tabs[oldItem - 1].scaleX = (1 - fraction) * 0.2f + 1.0f
                                tabs[oldItem - 1].scaleY = (1 - fraction) * 0.2f + 1.0f
                            }

                            if (positionOffset < 0.1f) {
                                tabs[oldItem - 1].setTextColor(endColor)
                                tabs[oldItem].setTextColor(startColor)
                                tabs[oldItem - 1].scaleX = 1.2f
                                tabs[oldItem - 1].scaleY = 1.2f
                                tabs[oldItem].scaleX = 1.0f
                                tabs[oldItem].scaleY = 1.0f
                            }

                            if (positionOffset > 0.9f) {
                                tabs[oldItem].setTextColor(endColor)
                                tabs[oldItem - 1].setTextColor(startColor)
                                tabs[oldItem - 1].scaleX = 1.0f
                                tabs[oldItem - 1].scaleY = 1.0f
                                tabs[oldItem].scaleX = 1.2f
                                tabs[oldItem].scaleY = 1.2f
                            }
                        }
                    }
                }

                override fun onPageSelected(position: Int) {
                    Timber.d("onPageSelected %s", position)
                }
            })

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