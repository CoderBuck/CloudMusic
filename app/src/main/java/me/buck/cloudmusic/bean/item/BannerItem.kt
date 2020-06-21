package me.buck.cloudmusic.bean.item

import me.buck.cloudmusic.bean.em.EmTitleColor

data class BannerItem(
    val pic: String,
//    val targetType: String
    val titleColor: EmTitleColor, // blue, red
    val typeTitle: String
) {
}