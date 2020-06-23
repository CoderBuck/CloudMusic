package me.buck.cloudmusic.view

import android.animation.ArgbEvaluator
import android.animation.FloatEvaluator
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ColorUtils
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.CustomviewHomeTabLayoutBinding

/**
 * 主页的 Tab
 */
class HomeTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private val bind: CustomviewHomeTabLayoutBinding

    private val argbEvaluator = ArgbEvaluator()
    private val startColor = ColorUtils.getColor(R.color.tab_normal)
    private val endColor = ColorUtils.getColor(R.color.tab_select)

    private val textSizeEvaluator = FloatEvaluator()
    private val startSize = resources.getDimension(R.dimen.home_tab_text_size_normal)
    private val endSize = resources.getDimension(R.dimen.home_tab_text_size_select)

    private val tabs: List<TextView>
    private var vp: ViewPager? = null

    init {
        bind = CustomviewHomeTabLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        tabs = listOf(bind.tab1, bind.tab2, bind.tab3, bind.tab4)

        bind.tab1.setOnClickListener { selectPage(0) }
        bind.tab2.setOnClickListener { selectPage(1) }
        bind.tab3.setOnClickListener { selectPage(2) }
        bind.tab4.setOnClickListener { selectPage(3) }

    }

    private fun selectPage(page: Int) {
        vp?.currentItem = page
    }

    fun setupWithViewPager(vp: ViewPager) {
        this.vp = vp
        for (i in tabs.indices) {
            if (i == vp.currentItem) {
                tabs[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, endSize)
                tabs[i].setTextColor(endColor)
                tabs[i].paint.isFakeBoldText = true
            } else {
                tabs[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, startSize)
                tabs[i].setTextColor(startColor)
                tabs[i].paint.isFakeBoldText = false
            }
        }

        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            var prePositionOffset = 0f
            var isUserDraged = false

            override fun onPageScrollStateChanged(state: Int) {
                isUserDraged = state == ViewPager.SCROLL_STATE_DRAGGING
                        || (isUserDraged && state == ViewPager.SCROLL_STATE_SETTLING)
            }

            // position: 左边第一个可见page的索引
            // positionOffset: 第一个可见page的不可见部分比例 [0,1]，也就是 pos+1 的可见的比例
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position == 3) return
                if (!isUserDraged) return
//                if (abs(prePositionOffset - positionOffset) < 0.05f) return
                prePositionOffset = positionOffset
                val color1 = argbEvaluator.evaluate(1 - positionOffset, startColor, endColor) as Int
                val color2 = argbEvaluator.evaluate(positionOffset, startColor, endColor) as Int
                tabs[position].setTextColor(color1)
                tabs[position + 1].setTextColor(color2)

                val size1 = textSizeEvaluator.evaluate(1 - positionOffset, startSize, endSize)
                val size2 = textSizeEvaluator.evaluate(positionOffset, startSize, endSize)
                tabs[position].setTextSize(TypedValue.COMPLEX_UNIT_PX, size1)
                tabs[position + 1].setTextSize(TypedValue.COMPLEX_UNIT_PX, size2)

                if (positionOffset > 0.5f) {
                    tabs[position].paint.isFakeBoldText = false
                    tabs[position + 1].paint.isFakeBoldText = true
                } else {
                    tabs[position].paint.isFakeBoldText = true
                    tabs[position + 1].paint.isFakeBoldText = false
                }

            }

            override fun onPageSelected(position: Int) {
                if (isUserDraged) return
                for (i in tabs.indices) {
                    if (i == position) {
                        tabs[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, endSize)
                        tabs[i].setTextColor(endColor)
                        tabs[i].paint.isFakeBoldText = true
                    } else {
                        tabs[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, startSize)
                        tabs[i].setTextColor(startColor)
                        tabs[i].paint.isFakeBoldText = false
                    }
                }
            }
        })
    }
}