package me.buck.cloudmusic.adapter.fastadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.FastItemFuncButtonBinding

class FuncButtonItem(
    @StringRes val name: Int,
    @DrawableRes val img: Int
) : AbstractBindingItem<FastItemFuncButtonBinding>() {


    override val type: Int = R.id.fast_type_func_button

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): FastItemFuncButtonBinding {
        return FastItemFuncButtonBinding.inflate(inflater, parent, false)
    }


    override fun bindView(binding: FastItemFuncButtonBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.img.setImageResource(img)
        binding.name.setText(name)
    }
}