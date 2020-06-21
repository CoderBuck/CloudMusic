package me.buck.cloudmusic.activity.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.GsonUtils
import me.buck.cloudmusic.R
import me.buck.cloudmusic.bean.BannerResult
import me.buck.cloudmusic.bean.em.EmTitleColor
import me.buck.cloudmusic.bean.item.BannerItem
import me.buck.cloudmusic.databinding.TestActivityBannerBinding
import me.buck.cloudmusic.restapi.Api
import org.youma.util.contentView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BannerTestActivity : AppCompatActivity() {

    private lateinit var bind: TestActivityBannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_banner)
        bind = TestActivityBannerBinding.bind(contentView)
        test1()
    }

    private fun test1() {
        val banner = Api.cloudMusic.getBanner()
        banner.enqueue(object : Callback<BannerResult?> {
            override fun onFailure(call: Call<BannerResult?>, t: Throwable) {
                Timber.e(t, "onFailure")

                val bannerItems = listOf(
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家"),
                    BannerItem("", EmTitleColor.blue, "独家")
                )

                bind.banner.setData(bannerItems)

            }

            override fun onResponse(call: Call<BannerResult?>, response: Response<BannerResult?>) {
                Timber.d("onResponse")
                val body = response.body()
                if (body != null) {
                    val json = GsonUtils.toJson(body)
                    Timber.d("body = %s", json)
                    val items = body.banners.map {
                        BannerItem(it.pic, it.titleColor, it.typeTitle)
                    }.toList()
                    bind.banner.setData(items)
                }
            }
        })
    }
}