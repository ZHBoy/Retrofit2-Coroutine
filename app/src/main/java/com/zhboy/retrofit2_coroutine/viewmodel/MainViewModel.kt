package com.zhboy.retrofit2_coroutine.viewmodel

import androidx.lifecycle.*
import com.zhboy.retrofit2_coroutine.bean.GoodsBean
import com.zhboy.retrofit2_coroutine.network.request.ResultData
import com.zhboy.retrofit2_coroutine.network.request.addSource
import com.zhboy.retrofit2_coroutine.network.request.simpleRequestLiveData
import com.zhboy.retrofit2_coroutine.network.retrofit.HttpManager
import com.zhboy.retrofit2_coroutine.network.service.UserService

/**
 * @author 李沐阳
 * @date：2020/5/5
 * @description:
 */
class MainViewModel : ViewModel() {

    companion object {
        //双重校验锁式-单例 封装UserService
        val service: UserService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpManager.instance.createService(UserService::class.java)
        }
    }

    private val _newsLiveData = MediatorLiveData<ResultData<GoodsBean>>()

    // 对外暴露的只是抽象的LiveData，防止外部随意更改数据
    val newsLiveData: LiveData<ResultData<GoodsBean>>
        get() = _newsLiveData

    fun getNews() {
        val liveData = viewModelScope.simpleRequestLiveData<GoodsBean> {
            api { service.getGoods() }

            /**
             * 以下内容为可选实现
             */

            // 加载数据库缓存，直接返回 room 数据库的 LiveData
//            loadCache {
//                return@loadCache roomLiveData
//            }

            // 保存数据到 room 数据库
//            saveCache {
//            }
        }

        // 监听数据变化
        _newsLiveData.addSource(liveData)
    }
}