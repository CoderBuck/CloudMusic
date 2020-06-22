package me.buck.cloudmusic.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.buck.cloudmusic.R
import me.buck.cloudmusic.databinding.ActivityHomeBinding
import org.youma.util.contentView

class HomeActivity: AppCompatActivity() {

    lateinit var bind:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bind = ActivityHomeBinding.bind(contentView)
    }
}