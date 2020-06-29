package me.buck.cloudmusic.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import me.buck.cloudmusic.Config
import me.buck.cloudmusic.R
import me.buck.cloudmusic.adapter.fastadapter.BannerItem
import me.buck.cloudmusic.adapter.fastadapter.FuncButtonItem
import me.buck.cloudmusic.adapter.fastadapter.FuncButtonsItem
import me.buck.cloudmusic.databinding.FragmentDiscoveryBinding
import me.buck.cloudmusic.util.delegate.viewBinding
import me.buck.cloudmusic.viewmodel.DiscoveryViewModel
import timber.log.Timber

class DiscoveryFragment : Fragment(R.layout.fragment_discovery) {

    private val bind by viewBinding(FragmentDiscoveryBinding::bind)
    private val model: DiscoveryViewModel by viewModels()
    private val bannerAdapter = ItemAdapter<BannerItem>()
    private val funcButtonsAdapter = ItemAdapter<FuncButtonsItem>()
    private lateinit var fastAdapter: GenericFastAdapter


    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        model.request()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        initData()
        observer()
    }

    private fun bindView() {
        bind.root.updatePadding(top = Config.statusBarHeight)
        bind.rv.layoutManager = LinearLayoutManager(context)
        val adapters: Collection<ItemAdapter<out GenericItem>> = listOf(bannerAdapter, funcButtonsAdapter)
        fastAdapter = GenericFastAdapter.with(adapters)
        bind.rv.adapter = fastAdapter
    }

    private fun initData() {
        val funcButtonItems = Config.funcButtons.map { FuncButtonItem(it.first, it.second) }
        bannerAdapter.add(BannerItem(emptyList()))
        funcButtonsAdapter.add(FuncButtonsItem(funcButtonItems))
    }

    private fun observer() {
        model.banners.observe(viewLifecycleOwner, Observer {
            bannerAdapter[0] = BannerItem(it)
        })
    }
}