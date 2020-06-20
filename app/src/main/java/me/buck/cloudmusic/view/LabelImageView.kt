package me.buck.cloudmusic.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.makeramen.roundedimageview.RoundedImageView
import me.buck.cloudmusic.R

/**
 * 包含一张图片和标签（目前右下角）
 */
class LabelImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val image: RoundedImageView
    val label: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.customview_label_image_view, this)
        image = findViewById(R.id.image)
        label = findViewById(R.id.label)
    }
}