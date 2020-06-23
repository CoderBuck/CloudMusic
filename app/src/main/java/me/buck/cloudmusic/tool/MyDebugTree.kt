package me.buck.cloudmusic.tool

import timber.log.Timber

class MyDebugTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, "gwf-$tag", message, t)
    }
}