package me.buck.cloudmusic.util

import androidx.annotation.DimenRes
import com.blankj.utilcode.util.Utils

object ResUtils {

    val resources = Utils.getApp().resources

    fun getDimension(@DimenRes id: Int) = resources.getDimension(id)


}