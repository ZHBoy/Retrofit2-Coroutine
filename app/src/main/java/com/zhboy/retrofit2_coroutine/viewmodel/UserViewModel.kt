package com.zhboy.retrofit2_coroutine.viewmodel

import androidx.lifecycle.*
import com.zhboy.retrofit2_coroutine.bean.GoodsBean
import com.zhboy.retrofit2_coroutine.network.retrofit.HttpManager
import com.zhboy.retrofit2_coroutine.network.service.UserService
import com.zhboy.retrofit2_coroutine.network.request.simpleRequestLiveData

/**
 * @author zhou_hao
 * @date 2021/1/13
 * @description:
 */
class UserViewModel : ViewModel() {

    companion object {
        //双重校验锁式-单例 封装UserService
        val service: UserService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpManager.instance.createService(UserService::class.java)
        }
    }

    fun getGoods() = liveData {
        val liveData = viewModelScope.simpleRequestLiveData<GoodsBean> {

            // 加载数据库缓存，直接返回 room 数据库的 LiveData
//            loadCache {
//                return@loadCache roomLiveData
//            }

            // 保存数据到 room 数据库
//            saveCache {
//            }

            api { service.getGoods() }
        }

        this.emitSource(liveData)
    }
}