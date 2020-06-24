package me.buck.cloudmusic.adapter.fastadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import me.buck.cloudmusic.R
import me.buck.cloudmusic.bean.Banner
import me.buck.cloudmusic.databinding.FastItemBannerBinding
import me.buck.cloudmusic.databinding.FastItemFuncButtonsBinding

class FuncButtonsItem(

) : AbstractBindingItem<FastItemFuncButtonsBinding>() {

    override val type: Int = R.id.fast_type_func_buttons

    override fun bindView(binding: FastItemFuncButtonsBinding, payloads: List<Any>) {
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): FastItemFuncButtonsBinding {
        return FastItemFuncButtonsBinding.inflate(inflater, parent, false)
    }
}