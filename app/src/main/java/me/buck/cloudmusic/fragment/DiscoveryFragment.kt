package me.buck.cloudmusic.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import me.buck.cloudmusic.Config
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.FragmentDiscoveryBinding
import me.buck.cloudmusic.util.delegate.viewBinding
import me.buck.cloudmusic.viewmodel.DiscoveryViewModel
import timber.log.Timber

class DiscoveryFragment : Fragment(R.layout.fragment_discovery) {

    private val bind by viewBinding(FragmentDiscoveryBinding::bind)
    private val model: DiscoveryViewModel by viewModels()

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
        model.bannerItems.observe(viewLifecycleOwner, Observer {
            bind.banner.setData(it)
        })
    }
}