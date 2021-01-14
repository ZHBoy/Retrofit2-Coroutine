package com.zhboy.retrofit2_coroutine.network.service

import com.zhboy.retrofit2_coroutine.bean.GoodsBean
import com.zhboy.retrofit2_coroutine.network.ApiConstants
import retrofit2.http.*

/**
 * @author zhou_hao
 * @date 2021/1/13
 * @description: 用户接口service
 */
interface UserService {

    /**
     * 登陆接口
     */
    @POST(ApiConstants.API_URL_USER_LOGIN)
    @FormUrlEncoded
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): String

    /**
     * 商品接口
     */
    @GET(ApiConstants.API_URL_GOOD_INFO)
    suspend fun getGoods(): GoodsBean

}