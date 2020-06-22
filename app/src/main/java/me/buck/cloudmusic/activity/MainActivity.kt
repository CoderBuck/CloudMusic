package me.buck.cloudmusic.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.buck.cloudmusic.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("onCreate")

//        startActivity(Intent(this, BannerTestActivity::class.java))
//        startActivity(Intent(this, ImageTestActivity::class.java))
        startActivity(Intent(this, HomeActivity::class.java))

//        finish()
    }
}