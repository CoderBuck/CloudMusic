package me.buck.cloudmusic.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

/**
 * 拓展类
 */

/* Activity */
val FragmentActivity.contentView: View
    get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)


/* Fragment */


/* RecyclerView */
fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.inflate(parent: ViewGroup, @LayoutRes layoutId: Int): View {
    return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
}

/* LiveData */
val <T>MutableLiveData<T>.valueNN: T
    get() = value!!

