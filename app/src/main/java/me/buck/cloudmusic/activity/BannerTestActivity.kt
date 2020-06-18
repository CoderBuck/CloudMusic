package me.buck.cloudmusic.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.GsonUtils
import me.buck.cloudmusic.R
import me.buck.cloudmusic.bean.BannerResult
import me.buck.cloudmusic.databinding.TestActivityBannerBinding
import me.buck.cloudmusic.restapi.Api
import org.youma.util.contentView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BannerTestActivity : AppCompatActivity(R.layout.test_activity_banner) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = TestActivityBannerBinding.bind(contentView)
        bind.imageView.setImageResource(R.drawable.test_banner)


        val banner = Api.cloudMusic.getBanner()
        banner.enqueue(object : Callback<BannerResult?> {
            override fun onFailure(call: Call<BannerResult?>, t: Throwable) {
                Timber.e(t, "onFailure")
            }

            override fun onResponse(call: Call<BannerResult?>, response: Response<BannerResult?>) {
                Timber.d("onResponse")
                val body = response.body()
                if (body != null) {
                    val json = GsonUtils.toJson(body)
                    Timber.d("body = %s", json)
                }
            }
        })
    }
}