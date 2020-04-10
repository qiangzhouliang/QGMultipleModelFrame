package qzl.com.main.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.google.gson.Gson
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.internal.platform.Platform
import org.jetbrains.anko.textColor
import qzl.com.basecommon.base.BaseFragment
import qzl.com.main.GetTranslationInterface
import qzl.com.main.mvp.model.HomeBean
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class YueDanFragment : BaseFragment(){
    override fun initView(): View? {
        var tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.textColor = Color.RED
        tv.text = javaClass.simpleName
        return tv
    }

    override fun initData() {
        // 创建Retrofit对象
        val retrofit = Retrofit.Builder()
            .baseUrl("http://v.juhe.cn/toutiao/")
            .client(getClient()?.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        // 创建网络请求接口的实例
        val request = retrofit.create(GetTranslationInterface::class.java)
//?type=top&key=e8159dfaee4760ddbcc13f6bb648d9e2&offset=1&size=10

        // 封装请求
        val observable: Observable<HomeBean> = request.getCall(hashMapOf(
            "type" to "top",
            "key" to "e8159dfaee4760ddbcc13f6bb648d9e2",
            "offset" to "1",
            "size" to "2"
        ))

        // 发送网络请求,子线程请求，主线程更新
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeBean>{
                override fun onComplete() {
                    println("请求成功")
                }

                override fun onSubscribe(d: Disposable) {
                    print("开始采用subscribe连接")
                }

                override fun onNext(t: HomeBean) {
                    println(t.result)
                }

                override fun onError(e: Throwable) {
                    print("请求失败")
                }
            })
    }

    private fun getClient(): OkHttpClient.Builder? {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS)
        //add log record
        if (true) {
            //打印网络请求日志
            val httpLoggingInterceptor: LoggingInterceptor = LoggingInterceptor.Builder()
                .loggable(true)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("请求")
                .response("响应")
                .build()
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return httpClientBuilder
    }

}