package me.buck.cloudmusic.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.kenilt.loopingviewpager.widget.LoopingViewPager
import me.buck.cloudmusic.R
import me.buck.cloudmusic.bean.Banner
import me.buck.cloudmusic.bean.item.BannerItem
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

    init {
        View.inflate(context, R.layout.customview_banner_view, this)
        viewPager = findViewById(R.id.looping_view_pager)
        indicator = findViewById(R.id.circle_indicator)
        adapter = BannerAdapter(context, emptyList())
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)
    }

    fun setData(banners: List<BannerItem>) {
        val adapter = BannerAdapter(context, banners)
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)
    }

}

class BannerAdapter(val context: Context, val items: List<BannerItem>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(context, R.layout.rvitem_banner, null)
        val labelImageView = view.findViewById<LabelImageView>(R.id.label_image_view)
        val banner = items[position]
        if (banner.pic.isNotBlank()) {
            Glide.with(context).load(banner.pic).centerCrop().into(labelImageView.image)
        } else {
            Glide.with(context).load(R.drawable.test_banner).centerCrop().into(labelImageView.image)
        }
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