package com.zhboy.retrofit2_coroutine.network.interceptor

import com.zhboy.retrofit2_coroutine.utils.GsonUtils
import okhttp3.*
import okio.Buffer
import java.io.IOException

/**
 * @author zhou_hao
 * @date 2021/1/13
 * @description: 公共参数拦截器
 */
class PublicParamsInterceptor2 : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        var newRequestBuild: Request.Builder? = oldRequest.newBuilder()
        val method = oldRequest.method()
        var postBodyString: String

        //公共参数
        val map = HashMap<String, Any?>()
        map["devicePlatform"] = "android"

        if ("POST" == method) {
            val oldBody = oldRequest.body()

            when (oldBody) {
                is FormBody -> {
                    newRequestBuild = oldRequest.newBuilder()
                    val newMap = HashMap<String, Any?>()
                    for (i in 0 until oldBody.size()) {
                        newMap[oldBody.name(i)] = oldBody.value(i)
                    }
                    for (obj in map) {
                        newMap[obj.key] = "${obj.value}"
                    }

                    //生成签名后的参数
                    postBodyString = GsonUtils.getIntGson().toJson(newMap)

                    newRequestBuild!!.post(
                        RequestBody.create(
                            MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"),
                            postBodyString
                        )
                    )
                }
                is MultipartBody -> {
                }
                else -> {
                    /* val oldMap = StringUtils.parseJSON2Map(bodyToString(oldBody))
                     val map3 = HashMap<String, Any?>()
                     if (oldMap != null) {
                         map3.putAll(oldMap)
                     }
                     map3.putAll(map)

 //                    postBodyString = StringUtils.getIntGson().toJson(map3)
 //                    TLog.i("postBodyString-", postBodyString)
 //                    postBodyString = AesUtils.aesEncryptAndBase64EncodeProcess(postBodyString)
 //                    TLog.i("postBodyString yan-", postBodyString)
 //                    val jsonMap = HashMap<String, Any?>()
 //                    jsonMap["params"] = postBodyString
 //                    jsonMap["sign"] = "AZH"

                     postBodyString = StringUtils.getIntGson().toJson(map3)
                     val lb = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), postBodyString)
                     newRequestBuild = oldRequest.newBuilder()
                     newRequestBuild.post(lb)*/
                }
            }

        } else {
            // 添加新的参数
            val commonParamsUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())

            commonParamsUrlBuilder.build()

            newRequestBuild = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(commonParamsUrlBuilder.build())
        }

        val newRequest = newRequestBuild!!
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "zh")
            .addHeader("Content-Type", "application/json")
            .addHeader("User-Agent", "youcai")
            .build()

        val response = chain.proceed(newRequest)
        val mediaType = response.body()!!.contentType()
        val content = response.body()!!.string()

        return response
            .newBuilder()
            .body(ResponseBody.create(mediaType, content))
            .header("application/json", "Content-Type")
            .addHeader("Content-Type", "application/json")
            .addHeader(
                "Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept"
            )
            .addHeader("Access-Control-Max-Age", "3600")
            .addHeader("Access-Control-Allow-Origin", "*")
            .addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
            .build()
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }
    }

}