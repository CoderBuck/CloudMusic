package me.buck.cloudmusic.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import me.buck.cloudmusic.bean.Banner
import me.buck.cloudmusic.databinding.ItemBannerBinding
import me.buck.cloudmusic.databinding.ViewBannerBinding

class BannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    val binding: ViewBannerBinding

    init {
        binding = ViewBannerBinding.inflate(LayoutInflater.from(context), this, true)
        val bannerAdapter = BannerAdapter(context, emptyList())
        binding.loopingViewPager.adapter = bannerAdapter
        binding.circleIndicator.setViewPager(binding.loopingViewPager)
    }

    fun setData(banners: List<Banner>) {
        val bannerAdapter = BannerAdapter(context, banners)
        binding.loopingViewPager.adapter = bannerAdapter
    }

}

class BannerAdapter(val context: Context, val items: List<Banner>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val bind = ItemBannerBinding.inflate(LayoutInflater.from(context), container, false)
        Glide.with(context).load(items[position].url).into(bind.img)
        container.addView(bind.root)
        return bind.root
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