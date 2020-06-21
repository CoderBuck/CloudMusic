package me.buck.cloudmusic.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.blankj.utilcode.util.ColorUtils
import com.makeramen.roundedimageview.RoundedImageView
import me.buck.cloudmusic.R
import me.buck.cloudmusic.bean.em.EmTitleColor

/**
 * 包含一张图片和标签（目前右下角）
 */
class LabelImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val image: RoundedImageView
    val label: TextView
    val shape = GradientDrawable()
    var radius = 0f
    var shapeColor: Int = 0

    init {
        View.inflate(context, R.layout.customview_label_image_view, this)
        image = findViewById(R.id.image)
        label = findViewById(R.id.label)
        val radius = resources.getDimension(R.dimen.img_corner_radius)
        image.cornerRadius = radius
        shape.cornerRadii = floatArrayOf(
            radius, radius,
            0f, 0f,
            radius, radius,
            0f, 0f
        )
        shape.setColor(ColorUtils.getColor(R.color.label_red))
        label.background = shape
    }

    fun setLabelColor(em: EmTitleColor) {
        shape.setColor(ColorUtils.getColor(em.colorId))
    }

    fun setLabelColor(@ColorInt color: Int) {
        shape.setColor(color)
    }

    fun setLabelName(name: String) {
        label.text = name
    }
}