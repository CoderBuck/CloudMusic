package me.buck.cloudmusic.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my.*
import me.buck.cloudmusic.R

class VideoFragment : Fragment(R.layout.fragment_my) {

    companion object{
        fun newInstance() = VideoFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv.setText(R.string.tab_item_video)
    }
}