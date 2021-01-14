package com.zhboy.retrofit2_coroutine.network

/**
 * @author : HaoBoy
 * @date : 2019/4/10
 * @description :接口相关
 **/
class ApiConstants private constructor() {

    companion object {

        const val IS_DEBUG = true

        const val IS_XIAOMI = true

        const val BASE_URL = "https://caledndardes.zyhzdm.net/"

        //用户登陆接口
        const val API_URL_USER_LOGIN = "user/login"

        //商品接口
        const val API_URL_GOOD_INFO = "calendar/otherFunction/getOnlineSpecialGoods"

    }
}