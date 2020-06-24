package me.buck.cloudmusic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.buck.cloudmusic.bean.Banner
import me.buck.cloudmusic.bean.BannerResult
import me.buck.cloudmusic.restapi.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class DiscoveryViewModel : ViewModel() {

    val banners = MutableLiveData<List<Banner>>()

    fun request() {
        val banner = Api.cloudMusic.getBanner()
        banner.enqueue(object : Callback<BannerResult?> {
            override fun onFailure(call: Call<BannerResult?>, t: Throwable) {
                Timber.e(t, "onFailure")
                /*val items = listOf(
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家")
                )
                bannerItems.value = items*/
            }

            override fun onResponse(call: Call<BannerResult?>, response: Response<BannerResult?>) {
                Timber.d("onResponse")
                val body = response.body()
                if (body != null) {
                    banners.value = body.banners
                }
            }
        })
    }
}