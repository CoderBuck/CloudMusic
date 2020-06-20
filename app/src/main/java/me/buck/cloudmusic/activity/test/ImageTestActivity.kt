package me.buck.cloudmusic.activity.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.test_activity_image.*
import me.buck.cloudmusic.R

class ImageTestActivity : AppCompatActivity(R.layout.test_activity_image) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        label_image_view.image
        label_image_view.label

        Glide.with(this)
            .load(R.drawable.test_banner)
            .centerCrop()
            .into(label_image_view.image)

        label_image_view.label.text = "测试"

    }
}