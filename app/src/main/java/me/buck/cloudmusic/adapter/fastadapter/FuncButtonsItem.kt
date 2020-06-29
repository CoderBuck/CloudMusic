package me.buck.cloudmusic.adapter.fastadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.FastItemFuncButtonsBinding
import me.buck.cloudmusic.tool.SpaceItemDecoration
import me.buck.cloudmusic.util.ResUtils

class FuncButtonsItem(
    private val funcButtonItems: List<FuncButtonItem>
) : AbstractBindingItem<FastItemFuncButtonsBinding>() {

    private lateinit var fastAdapter: FastItemAdapter<FuncButtonItem>


    override val type: Int = R.id.fast_type_func_buttons

    override fun bindView(binding: FastItemFuncButtonsBinding, payloads: List<Any>) {
        fastAdapter.set(funcButtonItems)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): FastItemFuncButtonsBinding {
        fastAdapter = FastItemAdapter()
        val left = ResUtils.getDimension(R.dimen.func_button_space).toInt()
        val space = SpaceItemDecoration(left = left)
        val llm = LinearLayoutManager(parent!!.context, LinearLayoutManager.HORIZONTAL, false)
        val binding = FastItemFuncButtonsBinding.inflate(inflater, parent, false)
        binding.rv.layoutManager = llm
        binding.rv.addItemDecoration(space)
        binding.rv.adapter = fastAdapter
        return binding
    }
}