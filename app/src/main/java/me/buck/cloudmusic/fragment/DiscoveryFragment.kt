package me.buck.cloudmusic.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.buck.cloudmusic.Config
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.FragmentDiscoveryBinding
import me.buck.cloudmusic.viewmodel.DiscoveryViewModel
import org.youma.util.provideViewModel
import timber.log.Timber

class DiscoveryFragment : Fragment(R.layout.fragment_discovery) {

    lateinit var model: DiscoveryViewModel

    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        model = provideViewModel(DiscoveryViewModel::class.java)
        model.request()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        val bind = FragmentDiscoveryBinding.bind(view)
        bind.root.updatePadding(top = Config.statusBarHeight)
        model.bannerItems.observe(viewLifecycleOwner, Observer {
            bind.banner.setData(it)
        })
    }
}