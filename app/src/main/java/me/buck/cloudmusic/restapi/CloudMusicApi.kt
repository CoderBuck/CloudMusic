package me.buck.cloudmusic.restapi

import me.buck.cloudmusic.bean.BannerResult
import retrofit2.Call
import retrofit2.http.GET

interface CloudMusicApi {

    /**
     * 获取 banner( 轮播图 ) 数据
     * type: 0: pc, 1: android, 2: iphone, 3: ipad
     */
    @GET("/banner?type=1")
    fun getBanner() : Call<BannerResult>

}