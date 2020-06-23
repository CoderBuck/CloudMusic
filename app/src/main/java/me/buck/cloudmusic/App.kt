package me.buck.cloudmusic

import android.app.Application
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.Utils
import me.buck.cloudmusic.tool.MyDebugTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Timber.plant(MyDebugTree())
        Config.statusBarHeight = BarUtils.getStatusBarHeight()
    }
}