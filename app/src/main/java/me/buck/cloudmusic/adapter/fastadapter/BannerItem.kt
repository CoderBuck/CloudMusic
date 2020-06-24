package me.buck.cloudmusic.adapter.fastadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import me.buck.cloudmusic.R
import me.buck.cloudmusic.bean.Banner
import me.buck.cloudmusic.databinding.FastItemBannerBinding

class BannerItem(
    val banners: List<Banner>
) : AbstractBindingItem<FastItemBannerBinding>() {


    override val type: Int = R.id.fast_type_banner

    override fun bindView(binding: FastItemBannerBinding, payloads: List<Any>) {
        binding.banner.setData(banners)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): FastItemBannerBinding {
        return FastItemBannerBinding.inflate(inflater, parent, false)
    }
}