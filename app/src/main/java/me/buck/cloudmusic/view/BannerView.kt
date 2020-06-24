package me.buck.cloudmusic.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.blankj.utilcode.util.ReflectUtils
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.kenilt.loopingviewpager.scroller.AutoScroller
import com.kenilt.loopingviewpager.widget.LoopingViewPager
import me.buck.cloudmusic.R
import me.buck.cloudmusic.bean.Banner
import me.buck.cloudmusic.tool.FixedSpeedScroller
import me.relex.circleindicator.CircleIndicator

/**
 * 指示器配置
 * @see R.drawable.indicator_grey
 * @see R.drawable.indicator_red
 * @see R.animator.indicator
 * @see R.dimen.banner_indicator_margin_bottom
 *
 * ViewPager 配置
 * @see R.dimen.banner_item_padding
 * @see R.dimen.img_corner_radius
 * @see R.dimen.banner_label_text
 * @see R.dimen.banner_label_padding_v
 * @see R.dimen.banner_label_padding_h
 * @see R.color.label_text
 */
class BannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    val viewPager: LoopingViewPager
    val indicator: CircleIndicator
    val adapter: BannerAdapter
//    val autoScroller: AutoScroller


    init {
        View.inflate(context, R.layout.customview_banner_view, this)
        viewPager = findViewById(R.id.looping_view_pager)
        indicator = findViewById(R.id.circle_indicator)
        adapter = BannerAdapter(context, emptyList())
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)

        val lifecycle = when (context) {
            is AppCompatActivity -> context.lifecycle
            is Fragment -> context.lifecycle
            else -> null
        }
//        autoScroller = AutoScroller(viewPager, lifecycle)
//        autoScroller.isAutoScroll = true

        // 修改 viewpager 滚动速度，慢一点
        // https://stackoverflow.com/a/9731345/6241791
        ReflectUtils.reflect(viewPager).field("mScroller", FixedSpeedScroller(context))
    }

    fun setData(banners: List<Banner>) {
        val adapter = BannerAdapter(context, banners)
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //动态调整下 banner 的高度，要保证内部 banner 图片宽高比 1080:420
        val bannerWidth = ScreenUtils.getScreenWidth().toFloat()
        val imageWidth = bannerWidth - resources.getDimension(R.dimen.banner_item_padding) * 2
        val imageHeight = imageWidth / 1080 * 420
        val bannerHeight = imageHeight + resources.getDimension(R.dimen.banner_item_padding) * 2
        val wms = MeasureSpec.makeMeasureSpec(bannerWidth.toInt(), MeasureSpec.EXACTLY)
        val hms = MeasureSpec.makeMeasureSpec(bannerHeight.toInt(), MeasureSpec.EXACTLY)
        super.onMeasure(wms, hms)
    }

}

class BannerAdapter(val context: Context, val items: List<Banner>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(context, R.layout.rvitem_banner, null)
        val labelImageView = view.findViewById<LabelImageView>(R.id.label_image_view)
        val banner = items[position]
        if (banner.pic.isNotBlank()) {
            Glide.with(context).load(banner.pic).centerCrop().into(labelImageView.image)
        } else {
            Glide.with(context).load(R.drawable.test_banner).centerCrop().into(labelImageView.image)
        }
        labelImageView.setLabelColor(banner.titleColor)
        labelImageView.setLabelName(banner.typeTitle)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View?)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return items.size
    }

}