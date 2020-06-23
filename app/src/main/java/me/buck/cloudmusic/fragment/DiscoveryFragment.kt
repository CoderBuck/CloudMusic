package me.buck.cloudmusic.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_my.*
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.FragmentDiscoveryBinding
import me.buck.cloudmusic.viewmodel.DiscoveryViewModel
import org.youma.util.provideViewModel

class DiscoveryFragment : Fragment(R.layout.fragment_discovery) {

    lateinit var model: DiscoveryViewModel

    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = provideViewModel(DiscoveryViewModel::class.java)
        model.request()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentDiscoveryBinding.bind(view)

        bind.root.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }

        model.bannerItems.observe(viewLifecycleOwner, Observer {
            bind.banner.setData(it)
        })
    }
}