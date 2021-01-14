package com.zhboy.retrofit2_coroutine.network.retrofit

import com.zhboy.retrofit2_coroutine.network.ApiConstants
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author : HaoBoy
 * @date : 2019/4/10
 * @description :网络请求管理器
 **/
class HttpManager private constructor() {

    companion object {
        val instance: HttpManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HttpManager() }
    }

    private var msg: String = ""

    private var option: RequestOption? = null

    fun showProgress(msg: String): HttpManager {
        this.msg = msg
        return this
    }

    fun setOption(option: RequestOption?): HttpManager {
        this.option = option
        return this
    }

    fun <T> createService(serviceClass: Class<T>, baseUrl: String? = ApiConstants.BASE_URL): T {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())// json转换器
            .baseUrl(baseUrl ?: ApiConstants.BASE_URL)

        return retrofitBuilder
            .client(OkHttpClientUtil.instance)
            .build()
            .create(serviceClass)
    }

    //将参数值转成RequestBoby
    fun toRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }
}