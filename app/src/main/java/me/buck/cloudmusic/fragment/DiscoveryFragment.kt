package me.buck.cloudmusic.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_discovery.*
import me.buck.cloudmusic.Config
import me.buck.cloudmusic.R
import me.buck.cloudmusic.adapter.fastadapter.BannerItem
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
        bind.root.updatePadding(top = Config.statusBarHeight)
        bind.rv.layoutManager = LinearLayoutManager(context)
        val adapters: Collection<ItemAdapter<out GenericItem>> = listOf(bannerAdapter, funcButtonsAdapter)
        fastAdapter = GenericFastAdapter.with(adapters)
        bind.rv.adapter = fastAdapter


        bannerAdapter.add(BannerItem(emptyList()))
        funcButtonsAdapter.add(FuncButtonsItem())

        model.banners.observe(viewLifecycleOwner, Observer {
//            bannerAdapter.remove(0)
//            bannerAdapter.add(0, BannerItem(it))
            bannerAdapter[0] = BannerItem(it)
        })

        bind.btn.setOnClickListener {
            val adapterItem = bannerAdapter.getAdapterItem(0)
            val toMutableList = adapterItem.banners.toMutableList()
            toMutableList.removeAt(0)
            bannerAdapter.set(0, BannerItem(toMutableList))
        }

        bind.btn2.setOnClickListener {
            val adapterItem = bannerAdapter.getAdapterItem(0)
            val toMutableList = adapterItem.banners.toMutableList()
            toMutableList.removeAt(0)
            bannerAdapter.remove(0)
            bannerAdapter.add(0, BannerItem(toMutableList))
        }


    }
}