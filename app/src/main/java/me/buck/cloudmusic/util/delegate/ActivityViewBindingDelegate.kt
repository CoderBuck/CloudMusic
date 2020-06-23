package me.buck.cloudmusic.util.delegate

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingDelegate<T : ViewBinding>(
    val activity: AppCompatActivity,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<AppCompatActivity, T> {
    private var binding: T? = null


    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = activity.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return viewBindingFactory(thisRef.contentView).also { this.binding = it }
    }
}

fun <T : ViewBinding> AppCompatActivity.viewBinding(viewBindingFactory: (View) -> T) =
    ActivityViewBindingDelegate(this, viewBindingFactory)

val AppCompatActivity.contentView: View
    get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

