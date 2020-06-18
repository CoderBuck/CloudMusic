package me.buck.cloudmusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.buck.cloudmusic.activity.BannerTestActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("onCreate")

        startActivity(Intent(this, BannerTestActivity::class.java))
    }
}