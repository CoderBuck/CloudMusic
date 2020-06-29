package me.buck.cloudmusic.tool

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    val left: Int = 0,
    val top: Int = 0,
    val right: Int = 0,
    val bottom: Int = 0,
    val ignoreFirst: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (ignoreFirst && parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0, 0, 0, 0)
            return
        }
        outRect.set(left, top, right, bottom)
    }
}