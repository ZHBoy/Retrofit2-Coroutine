package com.zhboy.retrofit2_coroutine.network

/**
 * @author : HaoBoy
 * @date : 2019/4/10
 * @description :接口相关
 **/
class ApiConstants private constructor() {

    companion object {

        const val IS_DEBUG = true

        //可以换成自己的host
        const val BASE_URL = "https://caledndardes.zyhzdm.net/"

        //可以换成自己的path
        const val API_URL_USER_LOGIN = "user/login"

        //可以换成自己的path
        const val API_URL_GOOD_INFO = "calendar/otherFunction/getOnlineSpecialGoods"

    }
}