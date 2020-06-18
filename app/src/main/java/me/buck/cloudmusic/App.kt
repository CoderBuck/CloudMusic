package me.buck.cloudmusic

import android.app.Application
import com.blankj.utilcode.util.Utils
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "gwf-$tag", message, t)
            }
        })
    }
}