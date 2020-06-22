package me.buck.cloudmusic.activity

import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.ActivityHomeBinding
import me.buck.cloudmusic.util.TabUtils
import org.youma.util.contentView
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    lateinit var bind: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bind = ActivityHomeBinding.bind(contentView)
        setupInsets()

        val textView = TabUtils.getTextView(bind.tabs, 0)
        textView.text
        Timber.d("textView = %s", textView.text)

    }

    private fun setupInsets() {
        bind.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        bind.appbar.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }
    }
}